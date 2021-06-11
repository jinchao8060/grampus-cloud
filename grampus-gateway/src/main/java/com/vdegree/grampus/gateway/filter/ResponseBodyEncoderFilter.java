package com.vdegree.grampus.gateway.filter;

import com.google.common.collect.Maps;
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
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.NettyWriteResponseFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.rewrite.CachedBodyOutputMessage;
import org.springframework.cloud.gateway.filter.factory.rewrite.MessageBodyDecoder;
import org.springframework.cloud.gateway.filter.factory.rewrite.MessageBodyEncoder;
import org.springframework.cloud.gateway.filter.factory.rewrite.RewriteFunction;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.client.reactive.ClientHttpResponse;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.springframework.cloud.gateway.support.GatewayToStringStyler.filterToStringCreator;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.ORIGINAL_RESPONSE_CONTENT_TYPE_ATTR;

/**
 * 响应数据加密过滤器
 *
 * @author Beck
 * @since 2021-06-10
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ResponseBodyEncoderFilter extends AbstractGatewayFilterFactory {

	private final Map<String, MessageBodyDecoder> messageBodyDecoders = Collections.emptyMap();

	private final Map<String, MessageBodyEncoder> messageBodyEncoders = Collections.emptyMap();

	private final ServerCodecConfigurer codecConfigurer;

	private final URIDecoderProperties uriDecoderProperties;

	private static AntPathMatcher antPathMatcher = new AntPathMatcher();

	@Override
	public GatewayFilter apply(Object ignore) {
		Config config = new Config();
		return new ModifyResponseGatewayFilter(
			config.setRewriteFunction(Object.class, Object.class, getRewriteFunction()));
	}

	private RewriteFunction<Object, Object> getRewriteFunction() {
		return (exchange, resp) -> {
			ServerHttpRequest request = exchange.getRequest();
			String headerName = uriDecoderProperties.getHeaderName();
			String encryptKey = request.getHeaders().getFirst(headerName);
			String platform = request.getHeaders().getFirst("platform");
			String requestUri = request.getPath().pathWithinApplication().value();
			String privateKey = RequestPlatformEnum.ADMIN.getPlatform().equals(platform) ?
				uriDecoderProperties.getAdminPrivateKey() : uriDecoderProperties.getPrivateKey();
			List<String> responseIgnoreUris = uriDecoderProperties.getResponseIgnoreUrls();
			// if request header contains Encrypt-Key or uri not match responseIgnoreUris, then encrypt
			boolean isIgnoreUris = responseIgnoreUris != null && pathMatcher(requestUri, responseIgnoreUris);
			if (StringUtil.isBlank(encryptKey) || isIgnoreUris) {
				return Mono.just(resp);
			}
			// encrypt
			String aesKey = RSAUtil.decryptFromBase64(privateKey, encryptKey);
			if (StringUtil.isBlank(aesKey)) {
				log.error("RequestBodyDecoderFilter error. aesKey is null. privateKey:{} encryptKey:{}", privateKey, encryptKey);
				throw new BaseException(ErrorCode.Gateway.GATEWAY_PARAMS_DECODE_ERROR.getCode(), "encryptKey is error.");
			}
			Map<String, String> result = Maps.newHashMap();
			result.put("cipherText", AESUtil.encryptToBase64(JSONUtil.writeValueAsString(resp), aesKey));
			return Mono.just(result);
		};
	}

	private boolean pathMatcher(String requestUri, List<String> urls) {
		for (String url : urls) {
			if (antPathMatcher.match(url, requestUri)) {
				return true;
			}
		}
		return false;
	}

	public static class Config {

		private Class inClass;

		private Class outClass;

		private Map<String, Object> inHints;

		private Map<String, Object> outHints;

		private String newContentType;

		private RewriteFunction rewriteFunction;

		public Class getInClass() {
			return inClass;
		}

		public Config setInClass(Class inClass) {
			this.inClass = inClass;
			return this;
		}

		public Class getOutClass() {
			return outClass;
		}

		public Config setOutClass(Class outClass) {
			this.outClass = outClass;
			return this;
		}

		public Map<String, Object> getInHints() {
			return inHints;
		}

		public Config setInHints(Map<String, Object> inHints) {
			this.inHints = inHints;
			return this;
		}

		public Map<String, Object> getOutHints() {
			return outHints;
		}

		public Config setOutHints(Map<String, Object> outHints) {
			this.outHints = outHints;
			return this;
		}

		public String getNewContentType() {
			return newContentType;
		}

		public Config setNewContentType(String newContentType) {
			this.newContentType = newContentType;
			return this;
		}

		public RewriteFunction getRewriteFunction() {
			return rewriteFunction;
		}

		public Config setRewriteFunction(RewriteFunction rewriteFunction) {
			this.rewriteFunction = rewriteFunction;
			return this;
		}

		public <T, R> Config setRewriteFunction(Class<T> inClass, Class<R> outClass,
																					   RewriteFunction<T, R> rewriteFunction) {
			setInClass(inClass);
			setOutClass(outClass);
			setRewriteFunction(rewriteFunction);
			return this;
		}

	}

	public class ModifyResponseGatewayFilter implements GatewayFilter, Ordered {

		private final Config config;

		private GatewayFilterFactory<Config> gatewayFilterFactory;

		public ModifyResponseGatewayFilter(Config config) {
			this(config, null);
		}

		@Deprecated
		public ModifyResponseGatewayFilter(Config config,
                                           @Nullable ServerCodecConfigurer codecConfigurer) {
			this.config = config;
		}

		@Override
		public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
			return chain.filter(exchange.mutate()
				.response(new ModifiedServerHttpResponse(exchange, config)).build());
		}

		@SuppressWarnings("unchecked")
		@Deprecated
		ServerHttpResponse decorate(ServerWebExchange exchange) {
			return new ModifiedServerHttpResponse(exchange, config);
		}

		@Override
		public int getOrder() {
			return NettyWriteResponseFilter.WRITE_RESPONSE_FILTER_ORDER - 1;
		}

		@Override
		public String toString() {
			Object obj = (this.gatewayFilterFactory != null) ? this.gatewayFilterFactory
				: this;
			return filterToStringCreator(obj)
				.append("New content type", config.getNewContentType())
				.append("In class", config.getInClass())
				.append("Out class", config.getOutClass()).toString();
		}

		public void setFactory(GatewayFilterFactory<Config> gatewayFilterFactory) {
			this.gatewayFilterFactory = gatewayFilterFactory;
		}

	}

	protected class ModifiedServerHttpResponse extends ServerHttpResponseDecorator {

		private final ServerWebExchange exchange;

		private final Config config;

		public ModifiedServerHttpResponse(ServerWebExchange exchange, Config config) {
			super(exchange.getResponse());
			this.exchange = exchange;
			this.config = config;
		}

		@SuppressWarnings("unchecked")
		@Override
		public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
			Class inClass = config.getInClass();
			Class outClass = config.getInClass();
			boolean isTextPlainContentType = exchange.getResponse()
				.getHeaders().getContentType().includes(MediaType.TEXT_PLAIN);
			if (isTextPlainContentType) {
				inClass = String.class;
			}

			String originalResponseContentType = exchange
				.getAttribute(ORIGINAL_RESPONSE_CONTENT_TYPE_ATTR);
			HttpHeaders httpHeaders = new HttpHeaders();
			// explicitly add it in this way instead of
			// 'httpHeaders.setContentType(originalResponseContentType)'
			// this will prevent exception in case of using non-standard media
			// types like "Content-Type: image"
			httpHeaders.add(HttpHeaders.CONTENT_TYPE, originalResponseContentType);

			ClientResponse clientResponse = prepareClientResponse(body, httpHeaders);

			// TODO: flux or mono
			Mono modifiedBody = extractBody(exchange, clientResponse, inClass)
				.flatMap(originalBody -> config.getRewriteFunction().apply(exchange,
					originalBody))
				.switchIfEmpty(Mono.defer(() -> (Mono) config.getRewriteFunction()
					.apply(exchange, null)));

			if (isTextPlainContentType) {
				BodyInserter bodyInserter = BodyInserters.fromPublisher(modifiedBody,
					String.class);
				CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(exchange,
					exchange.getResponse().getHeaders());
				return bodyInserter.insert(outputMessage, new BodyInserterContext())
					.then(Mono.defer(() -> {
						Mono<DataBuffer> messageBody = writeBody(getDelegate(),
							outputMessage, String.class);
						HttpHeaders headers = getDelegate().getHeaders();
						if (!headers.containsKey(HttpHeaders.TRANSFER_ENCODING)
							|| headers.containsKey(HttpHeaders.CONTENT_LENGTH)) {
							messageBody = messageBody.doOnNext(data -> headers
								.setContentLength(data.readableByteCount()));
						}
						// TODO: fail if isStreamingMediaType?
						return getDelegate().writeWith(messageBody);
					}));
			}

			BodyInserter bodyInserter = BodyInserters.fromPublisher(modifiedBody,
				outClass);
			CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(exchange,
				exchange.getResponse().getHeaders());
			return bodyInserter.insert(outputMessage, new BodyInserterContext())
				.then(Mono.defer(() -> {
					Mono<DataBuffer> messageBody = writeBody(getDelegate(),
						outputMessage, outClass);
					HttpHeaders headers = getDelegate().getHeaders();
					if (!headers.containsKey(HttpHeaders.TRANSFER_ENCODING)
						|| headers.containsKey(HttpHeaders.CONTENT_LENGTH)) {
						messageBody = messageBody.doOnNext(data -> headers
							.setContentLength(data.readableByteCount()));
					}
					// TODO: fail if isStreamingMediaType?
					return getDelegate().writeWith(messageBody);
				}));
		}

		@Override
		public Mono<Void> writeAndFlushWith(
			Publisher<? extends Publisher<? extends DataBuffer>> body) {
			return writeWith(Flux.from(body).flatMapSequential(p -> p));
		}

		private ClientResponse prepareClientResponse(Publisher<? extends DataBuffer> body,
													 HttpHeaders httpHeaders) {
			List<HttpMessageReader<?>> messageReaders = codecConfigurer.getReaders();
			ClientResponse.Builder builder;
			builder = ClientResponse.create(exchange.getResponse().getStatusCode(),
				messageReaders);
			return builder.headers(headers -> headers.putAll(httpHeaders))
				.body(Flux.from(body)).build();
		}

		private <T> Mono<T> extractBody(ServerWebExchange exchange,
										ClientResponse clientResponse, Class<T> inClass) {
			// if inClass is byte[] then just return body, otherwise check if
			// decoding required
			if (byte[].class.isAssignableFrom(inClass)) {
				return clientResponse.bodyToMono(inClass);
			}

			List<String> encodingHeaders = exchange.getResponse().getHeaders()
				.getOrEmpty(HttpHeaders.CONTENT_ENCODING);
			for (String encoding : encodingHeaders) {
				MessageBodyDecoder decoder = messageBodyDecoders.get(encoding);
				if (decoder != null) {
					return clientResponse.bodyToMono(byte[].class)
						.publishOn(Schedulers.parallel()).map(decoder::decode)
						.map(bytes -> exchange.getResponse().bufferFactory()
							.wrap(bytes))
						.map(buffer -> prepareClientResponse(Mono.just(buffer),
							exchange.getResponse().getHeaders()))
						.flatMap(response -> response.bodyToMono(inClass));
				}
			}

			return clientResponse.bodyToMono(inClass);
		}

		private Mono<DataBuffer> writeBody(ServerHttpResponse httpResponse,
										   CachedBodyOutputMessage message, Class<?> outClass) {
			Mono<DataBuffer> response = DataBufferUtils.join(message.getBody());
			if (byte[].class.isAssignableFrom(outClass)) {
				return response;
			}

			List<String> encodingHeaders = httpResponse.getHeaders()
				.getOrEmpty(HttpHeaders.CONTENT_ENCODING);
			for (String encoding : encodingHeaders) {
				MessageBodyEncoder encoder = messageBodyEncoders.get(encoding);
				if (encoder != null) {
					DataBufferFactory dataBufferFactory = httpResponse.bufferFactory();
					response = response.publishOn(Schedulers.parallel()).map(buffer -> {
						byte[] encodedResponse = encoder.encode(buffer);
						DataBufferUtils.release(buffer);
						return encodedResponse;
					}).map(dataBufferFactory::wrap);
					break;
				}
			}

			return response;
		}

	}

	@Deprecated
	@SuppressWarnings("unchecked")
	public class ResponseAdapter implements ClientHttpResponse {

		private final Flux<DataBuffer> flux;

		private final HttpHeaders headers;

		public ResponseAdapter(Publisher<? extends DataBuffer> body,
							   HttpHeaders headers) {
			this.headers = headers;
			if (body instanceof Flux) {
				flux = (Flux) body;
			}
			else {
				flux = ((Mono) body).flux();
			}
		}

		@Override
		public Flux<DataBuffer> getBody() {
			return flux;
		}

		@Override
		public HttpHeaders getHeaders() {
			return headers;
		}

		@Override
		public HttpStatus getStatusCode() {
			return null;
		}

		@Override
		public int getRawStatusCode() {
			return 0;
		}

		@Override
		public MultiValueMap<String, ResponseCookie> getCookies() {
			return null;
		}

	}

}
