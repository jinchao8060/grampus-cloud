package com.vdegree.grampus.gateway.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.vdegree.grampus.common.core.exception.BaseException;
import com.vdegree.grampus.common.core.utils.JSONUtil;
import com.vdegree.grampus.common.core.utils.StringUtil;
import com.vdegree.grampus.common.core.utils.crypto.AESUtil;
import com.vdegree.grampus.common.core.utils.crypto.RSAUtil;
import com.vdegree.grampus.gateway.code.ErrorCode;
import com.vdegree.grampus.gateway.properties.URIDecoderProperties;
import com.vdegree.grampus.gateway.support.RequestPlatformEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.rewrite.CachedBodyOutputMessage;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 请求信息解密过滤器
 *
 * @author Beck
 * @since 2021-06-10
 */
@Slf4j
@Order(2)
@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(URIDecoderProperties.class)
public class RequestBodyDecoderFilter extends AbstractGatewayFilterFactory {

//    private final List<HttpMessageReader<?>> messageReaders = HandlerStrategies.withDefaults().messageReaders();

	private final ServerCodecConfigurer codecConfigurer;

    private final URIDecoderProperties uriDecoderProperties;

    private static AntPathMatcher antPathMatcher = new AntPathMatcher();

//    private static final String PRIVATE_KEY = "";
//    private static final String PUBLIC_KEY = "";
//
//    private static final String REQUEST_HEADER_ENCRYPT_KEY = "Encrypt-Key";

    @Override
    public GatewayFilter apply(Object config) {
        return new ModifyRequestGatewayFilter();
    }

    public class ModifyRequestGatewayFilter implements GatewayFilter {

		@Override
		public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
			String headerName = uriDecoderProperties.getHeaderName();
			String cipherField = uriDecoderProperties.getCipherField();
			List<String> filterUris = uriDecoderProperties.getUrls();
			ServerHttpRequest request = exchange.getRequest();
			String requestUri = request.getPath().pathWithinApplication().value();
			String platform = request.getHeaders().getFirst("platform");
			// if request header contains Encrypt-Key or uri match filterUris, then decrypt
			String encryptKey = request.getHeaders().getFirst(headerName);
			MediaType mediaType = request.getHeaders().getContentType();
			String privateKey = RequestPlatformEnum.ADMIN.getPlatform().equals(platform) ?
				uriDecoderProperties.getAdminPrivateKey() : uriDecoderProperties.getPrivateKey();
			boolean isEncryptRequest = StringUtil.isNotBlank(encryptKey)
				&& MediaType.APPLICATION_JSON.equals(mediaType);
			if (isEncryptRequest || pathMatcher(requestUri, filterUris)) {
				if (StringUtil.isBlank(encryptKey)) {
					log.error("RequestBodyDecoderFilter error. encryptKey is null. imei:{} privateKey:{} encryptKey:{}", request.getHeaders().getFirst("imei"), privateKey, encryptKey);
					throw new BaseException(ErrorCode.Gateway.GATEWAY_PARAMS_DECODE_ERROR.getCode(), "encryptKey is null.");
				}
				String aesKey = RSAUtil.decryptFromBase64(privateKey, encryptKey);
				if (StringUtil.isBlank(aesKey)) {
					log.error("RequestBodyDecoderFilter error. aesKey is null. imei:{} privateKey:{} encryptKey:{}", request.getHeaders().getFirst("imei"), privateKey, encryptKey);
					throw new BaseException(ErrorCode.Gateway.GATEWAY_PARAMS_DECODE_ERROR.getCode(), "encryptKey is error.");
				}
				// modify request body
				Class inClass = String.class;
				Class outClass = String.class;
				List<HttpMessageReader<?>> messageReaders = codecConfigurer.getReaders();
				ServerRequest serverRequest = ServerRequest.create(exchange, messageReaders);
				// decrypt
				Mono<?> modifiedBody = serverRequest.bodyToMono(inClass).flatMap(
					text -> Mono.just(decryptRequestBody(aesKey, (String)text, cipherField)));
				BodyInserter bodyInserter = BodyInserters.fromPublisher(modifiedBody, outClass);
				HttpHeaders headers = new HttpHeaders();
				headers.putAll(exchange.getRequest().getHeaders());
				// the new content type will be computed by bodyInserter
				// and then set in the request decorator
				headers.remove(HttpHeaders.CONTENT_LENGTH);
				headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
				CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(exchange, headers);
				return bodyInserter.insert(outputMessage, new BodyInserterContext()).then(Mono.defer(() -> {
					ServerHttpRequest decorator = decorate(exchange, headers, outputMessage);
					return chain.filter(exchange.mutate().request(decorator).build());
				}));
			}
			return chain.filter(exchange);
		}
	}

    private String decryptRequestBody(String aesKey, String text, String field) {
        JsonNode jsonNode =  JSONUtil.readTree(text);
        if (jsonNode != null) {
            return AESUtil.decryptFormBase64ToString(jsonNode.get(field).asText(), aesKey);
        } else {
            return AESUtil.decryptFormBase64ToString(text, aesKey);
        }
    }

    private ServerHttpRequestDecorator decorate(ServerWebExchange exchange, HttpHeaders headers,
                                                CachedBodyOutputMessage outputMessage) {
        return new ServerHttpRequestDecorator(exchange.getRequest()) {
            @Override
            public HttpHeaders getHeaders() {
                long contentLength = headers.getContentLength();
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.putAll(super.getHeaders());
                if (contentLength > 0) {
                    httpHeaders.setContentLength(contentLength);
                }
                else {
                    // TODO: this causes a 'HTTP/1.1 411 Length Required' // on
                    // httpbin.org
                    httpHeaders.set(HttpHeaders.TRANSFER_ENCODING, "chunked");
                }
                return httpHeaders;
            }

            @Override
            public Flux<DataBuffer> getBody() {
                return outputMessage.getBody();
            }
        };
    }

    private boolean pathMatcher(String requestUri, List<String> urls){
        for (String url : urls) {
            if(antPathMatcher.match(url, requestUri)){
                return true;
            }
        }
        return false;
    }
}
