package com.vdegree.grampus.gateway.filter;

import com.vdegree.grampus.common.core.utils.JSONUtil;
import com.vdegree.grampus.gateway.gray.GrayHeaderConsumer;
import com.vdegree.grampus.gateway.properties.NoAuthProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * 全局拦截器
 *
 * @author Beck
 * @since 2021-06-10
 */
@Slf4j
@Component
public class RequestGlobalFilter implements GlobalFilter, Ordered {
    private static final String REQUEST_HEADER_TOKEN = "token";

    @Autowired
    private NoAuthProperties noAuthProperties;
    private static AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    	// 匹配路由规则重写header版本号
		ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
				.headers(new GrayHeaderConsumer()).build();
		ServerWebExchange mutatedExchange = exchange.mutate().request(mutatedRequest).build();
		return chain.filter(mutatedExchange);
    }

    private Mono<Void> response(ServerWebExchange exchange, Object object) {
        return response(HttpStatus.OK, exchange, object);
    }

    private Mono<Void> response(HttpStatus httpStatus, ServerWebExchange exchange, Object object) {
        String json = JSONUtil.writeValueAsString(object);
        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(json.getBytes(StandardCharsets.UTF_8));
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
        exchange.getResponse().setStatusCode(httpStatus);
        return exchange.getResponse().writeWith(Flux.just(buffer));
    }

    private boolean pathMatcher(String requestUri){
        for (String url : noAuthProperties.getUrls()) {
            if(antPathMatcher.match(url, requestUri)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
