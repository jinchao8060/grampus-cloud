package com.vdegree.grampus.gateway.filter;

import com.vdegree.grampus.gateway.gray.GrayHeaderConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 全局拦截器
 *
 * @author Beck
 * @since 2021-06-10
 */
@Slf4j
@Component
public class RequestGlobalFilter implements GlobalFilter, Ordered {

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		// 匹配路由规则重写header版本号
		ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
				.headers(new GrayHeaderConsumer()).build();
		ServerWebExchange mutatedExchange = exchange.mutate().request(mutatedRequest).build();
		return chain.filter(mutatedExchange);
	}

	@Override
	public int getOrder() {
		return 0;
	}
}
