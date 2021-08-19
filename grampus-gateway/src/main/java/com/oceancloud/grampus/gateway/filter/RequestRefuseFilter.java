package com.oceancloud.grampus.gateway.filter;

import com.oceancloud.grampus.framework.core.exception.ApiException;
import com.oceancloud.grampus.gateway.code.ErrorCode;
import com.oceancloud.grampus.gateway.utils.WebFluxUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

/**
 * 请求拒绝过滤器
 *
 * @author Beck
 * @since 2021-06-10
 */
@Slf4j
@Order(1)
@Component
public class RequestRefuseFilter extends AbstractGatewayFilterFactory {

	@Override
	public GatewayFilter apply(Object config) {
		return (exchange, chain) -> {
			ServerHttpRequest request = exchange.getRequest();
			log.info("request refuse uri:{}, ip:{}", request.getURI(), WebFluxUtil.getIpAddress(request));
			throw new ApiException(ErrorCode.Gateway.GATEWAY_REQUEST_REFUSE_ERROR.getCode(), "illegal request.");
		};
	}
}
