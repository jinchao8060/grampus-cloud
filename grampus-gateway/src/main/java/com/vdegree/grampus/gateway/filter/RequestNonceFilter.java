package com.vdegree.grampus.gateway.filter;

import com.google.common.collect.Maps;
import com.vdegree.grampus.common.core.exception.ApiException;
import com.vdegree.grampus.common.core.utils.JSONUtil;
import com.vdegree.grampus.common.core.utils.StringUtil;
import com.vdegree.grampus.common.core.utils.crypto.DigestUtil;
import com.vdegree.grampus.common.core.utils.crypto.RSAUtil;
import com.vdegree.grampus.gateway.code.ErrorCode;
import com.vdegree.grampus.gateway.properties.URIDecoderProperties;
import com.vdegree.grampus.gateway.support.RequestPlatformEnum;
import com.vdegree.grampus.gateway.utils.WebFluxUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 请求防重放过滤器（网关暂不依赖Redis，使用本地缓存防重放，因此可重放次数为网关的数量）
 *
 * @author Beck
 * @since 2021-06-10
 */
@Slf4j
@Order(1)
@Component
@RequiredArgsConstructor
public class RequestNonceFilter extends AbstractGatewayFilterFactory {

	private final URIDecoderProperties uriDecoderProperties;

	private static final Long REQUEST_EXPIRE_TIME = 2 * 60 * 1000L;

	@Override
	public GatewayFilter apply(Object config) {
		return (exchange, chain) -> {
			ServerHttpRequest request = exchange.getRequest();
			HttpHeaders headers = request.getHeaders();
			String ts = headers.getFirst("Ts");
			String imei = headers.getFirst("Imei");
			String nonce = headers.getFirst("Nonce");
			String platform = headers.getFirst("Platform");
			String signKey = headers.getFirst("Signature-Key");
			String encryptKey = request.getHeaders().getFirst(uriDecoderProperties.getHeaderName());
			String privateKey = RequestPlatformEnum.ADMIN.getPlatform().equals(platform) ?
					uriDecoderProperties.getAdminPrivateKey() : uriDecoderProperties.getPrivateKey();

			Map<String, String> params = Maps.newLinkedHashMap();
			params.put("Platform", platform);
			params.put("Imei", imei);
			params.put("Nonce", nonce);
			params.put("Ts", ts);
			String nonceStr = JSONUtil.writeValueAsString(params);

			boolean isNonceRequest = StringUtil.isNotBlank(signKey);

			if (!isNonceRequest) {
				return chain.filter(exchange);
			}

			// platform invalidation.
			if (StringUtil.isBlank(platform)) {
				log.info("RequestNonceFilter refuse. platform invalidation. uri:{}, ip:{}",
						request.getURI(), WebFluxUtil.getIpAddress(request));
				throw new ApiException(ErrorCode.Gateway.GATEWAY_REQUEST_REFUSE_ERROR.getCode(), "platform invalidation.");
			}

			// timestamp invalidation.
			assert ts != null;
			if (System.currentTimeMillis() > Long.parseLong(ts) + REQUEST_EXPIRE_TIME) {
				log.info("RequestNonceFilter refuse. ts invalidation. uri:{}, ip:{}",
						request.getURI(), WebFluxUtil.getIpAddress(request));
				throw new ApiException(ErrorCode.Gateway.GATEWAY_REQUEST_REFUSE_ERROR.getCode(), "timestamp invalidation.");
			}

			// encryptKey is null.
			if (StringUtil.isBlank(encryptKey)) {
				log.info("RequestNonceFilter refuse. EncryptKey is null. uri:{}, ip:{}",
						request.getURI(), WebFluxUtil.getIpAddress(request));
				throw new ApiException(ErrorCode.Gateway.GATEWAY_REQUEST_REFUSE_ERROR.getCode(), "encryptKey is null.");
			}

			// signature mismatch.
			String aesKey = RSAUtil.decryptFromBase64(privateKey, encryptKey);
			String sign = DigestUtil.hmacSha256Hex(nonceStr, aesKey);
			if (!signKey.equals(sign)) {
				log.info("RequestNonceFilter refuse. Signature-Key exception. uri:{}, ip:{}, signKey:{}, realSignKey:{}, nonceStr:{}, encryptKey:{} aesKey:{}",
						request.getURI(), WebFluxUtil.getIpAddress(request), signKey, sign, nonceStr, headers.getFirst("Encrypt-Key"), aesKey);
				throw new ApiException(ErrorCode.Gateway.GATEWAY_REQUEST_REFUSE_ERROR.getCode(), "signature mismatch.");
			}

//			// nonce repeat.
//			String nonceCacheKey = platform + ":" + nonce;
//			boolean isContainsNonce = nonceTimeCache.containsKey(nonceCacheKey);
//			if (isContainsNonce) {
//				log.info("RequestNonceFilter refuse. nonce repeat. uri:{}, ip:{}, nonceStr:{}",
//					request.getURI(), WebFluxUtil.getIpAddress(request), nonceStr);
//				throw new ApiException(ErrorCode.REFUSE_ERROR_CODE);
//			} else {
//				nonceTimeCache.put(nonceCacheKey, System.currentTimeMillis());
//			}

			return chain.filter(exchange);
		};
	}
}
