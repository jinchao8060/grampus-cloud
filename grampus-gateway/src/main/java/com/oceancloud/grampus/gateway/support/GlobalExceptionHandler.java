package com.oceancloud.grampus.gateway.support;

import com.google.common.collect.Maps;
import com.oceancloud.grampus.framework.core.exception.ApiException;
import com.oceancloud.grampus.framework.core.result.Result;
import com.oceancloud.grampus.framework.core.utils.JSONUtil;
import com.oceancloud.grampus.framework.core.utils.StringUtil;
import com.oceancloud.grampus.framework.core.utils.crypto.AESUtil;
import com.oceancloud.grampus.framework.core.utils.crypto.RSAUtil;
import com.oceancloud.grampus.gateway.code.ErrorCode;
import com.oceancloud.grampus.gateway.properties.URIDecoderProperties;
import com.oceancloud.grampus.gateway.utils.WebFluxUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * 全局异常拦截
 *
 * @author Beck
 * @since 2021-06-10
 */
@Slf4j
@Primary
@Component
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler implements ErrorWebExceptionHandler {

	private final URIDecoderProperties uriDecoderProperties;

	@Override
	public Mono<Void> handle(ServerWebExchange exchange, Throwable throwable) {
		log.info("GLOBAL EXCEPTION:{}\n message:{}\n",
				exchange.getRequest().getPath(), throwable.getMessage(), throwable.getSuppressed()[0]);

		Result<Object> result;
		if (throwable instanceof ApiException) {
			ApiException ex = (ApiException) throwable;
			result = Result.error(ex.getCode(), ex.getMessage());
		} else {
			result = Result.error(ErrorCode.Global.UNKNOWN_ERROR_CODE.getCode(), ErrorCode.Global.UNKNOWN_ERROR_CODE.getMsg());
		}

		// encrypt response body
		ServerHttpRequest request = exchange.getRequest();
		String headerName = uriDecoderProperties.getHeaderName();
		String encryptKey = request.getHeaders().getFirst(headerName);
		String platform = request.getHeaders().getFirst("platform");
		String privateKey = RequestPlatformEnum.ADMIN.getPlatform().equals(platform) ?
				uriDecoderProperties.getAdminPrivateKey() : uriDecoderProperties.getPrivateKey();
		if (StringUtil.isNotBlank(encryptKey)) {
			String aesKey = RSAUtil.decryptFromBase64(privateKey, encryptKey);
			if (StringUtil.isNotBlank(aesKey)) {
				Map cipherResult = Maps.newHashMap();
				cipherResult.put("cipherText", AESUtil.encryptToBase64(JSONUtil.writeValueAsString(result), aesKey));
				WebFluxUtil.errorResponse(exchange, cipherResult);
			}
		}

		return WebFluxUtil.errorResponse(exchange, result);
	}
}