package com.oceancloud.grampus.gateway.utils;

import com.oceancloud.grampus.framework.core.utils.JSONUtil;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.lang.Nullable;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * WebFlux 工具
 *
 * @author Beck
 * @since 2021-06-10
 */
public class WebFluxUtil {

	/**
	 * 获取ip
	 *
	 * @param request HttpServletRequest
	 * @return {String}
	 */
	public static String getIpAddress(@Nullable ServerHttpRequest request) {
		HttpHeaders headers = request.getHeaders();
		String ip = headers.getFirst("x-forwarded-for");
		if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			if (ip.indexOf(",") != -1) {
				ip = ip.split(",")[0];
			}
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = headers.getFirst("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = headers.getFirst("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = headers.getFirst("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = headers.getFirst("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = headers.getFirst("X-Real-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddress().getAddress().getHostAddress();
		}
		return ip;
	}

	public static Mono<Void> successResponse(ServerWebExchange exchange, Object object) {
		return response(HttpStatus.OK, exchange, object);
	}

	public static Mono<Void> errorResponse(ServerWebExchange exchange, Object object) {
		return response(HttpStatus.INTERNAL_SERVER_ERROR, exchange, object);
	}

	/**
	 * WebFlux数据渲染
	 *
	 * @param httpStatus HttpStatus类常量
	 * @param exchange   exchange
	 * @param object     数据
	 */
	public static Mono<Void> response(HttpStatus httpStatus, ServerWebExchange exchange, Object object) {
		String json = JSONUtil.writeValueAsString(object);
		exchange.getResponse().setStatusCode(httpStatus);
		exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
		DataBuffer buffer = exchange.getResponse().bufferFactory()
				.wrap(json.getBytes(StandardCharsets.UTF_8));
		return exchange.getResponse().writeWith(Flux.just(buffer));
	}
}
