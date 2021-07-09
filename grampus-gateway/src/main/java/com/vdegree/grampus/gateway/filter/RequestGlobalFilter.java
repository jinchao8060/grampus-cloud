package com.vdegree.grampus.gateway.filter;

import com.vdegree.grampus.common.core.utils.StringUtil;
import com.vdegree.grampus.common.gray.constant.GrayLoadBalancerConstant;
import com.vdegree.grampus.gateway.gray.GrayVersionRewriteConsumer;
import com.vdegree.grampus.gateway.utils.JwtTokenUtil;
import com.vdegree.grampus.gateway.utils.WebFluxUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * 全局拦截器
 *
 * @author Beck
 * @since 2021-06-10
 */
@Slf4j
@Component
@AllArgsConstructor
public class RequestGlobalFilter implements GlobalFilter, Ordered {
	private static final String HEADER_INTERNAL_REQUEST_ID = "Internal-Request-Id";
	private final GrayVersionRewriteConsumer grayVersionRewriteConsumer;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
				// 清洗内部参数
				.headers(httpHeaders -> {
					httpHeaders.remove(GrayLoadBalancerConstant.HEADER_INTERNAL_VERSION);
					httpHeaders.remove(GrayLoadBalancerConstant.HEADER_INTERNAL_REQUEST_IP);
					httpHeaders.remove(GrayLoadBalancerConstant.HEADER_INTERNAL_REQUEST_SUBJECT);
					httpHeaders.remove(GrayLoadBalancerConstant.HEADER_INTERNAL_REQUEST_PLATFORM);
					httpHeaders.remove(HEADER_INTERNAL_REQUEST_ID);
				})
				// header添加RequestId
				.headers(httpHeaders -> {
					httpHeaders.add(HEADER_INTERNAL_REQUEST_ID,
							UUID.randomUUID().toString().replaceAll("-", ""));
				})
				// header添加请求IP,Subject,Platform
				.headers(httpHeaders -> {
					httpHeaders.add(GrayLoadBalancerConstant.HEADER_INTERNAL_REQUEST_IP,
							WebFluxUtil.getIpAddress(exchange.getRequest()));
					String token = httpHeaders.getFirst(GrayLoadBalancerConstant.HEADER_TOKEN);
					if (StringUtil.isNotBlank(token)) {
						httpHeaders.add(GrayLoadBalancerConstant.HEADER_INTERNAL_REQUEST_SUBJECT,
								JwtTokenUtil.getSubject(token));
						httpHeaders.add(GrayLoadBalancerConstant.HEADER_INTERNAL_REQUEST_PLATFORM,
								JwtTokenUtil.getPlatform(token));
					}
				})
				// 匹配路由规则重写header版本号
				.headers(grayVersionRewriteConsumer)
				.build();
		ServerWebExchange mutatedExchange = exchange.mutate().request(mutatedRequest).build();
		return chain.filter(mutatedExchange);
	}

	@Override
	public int getOrder() {
		return 0;
	}
}
