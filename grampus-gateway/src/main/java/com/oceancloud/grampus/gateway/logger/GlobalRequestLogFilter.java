package com.oceancloud.grampus.gateway.logger;

import com.google.common.base.Joiner;
import com.oceancloud.grampus.framework.core.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

/**
 * webflux 日志请求记录(方便开发调试 请求日志过滤器排序尽量低)
 *
 * @author Beck
 * @since 2021-06-10
 */
@Slf4j
@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
public class GlobalRequestLogFilter implements GlobalFilter, Ordered {
	public static final String START_NANO_TIME_KEY = "start_nano_time_key";
	private final WebEndpointProperties endpointProperties;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();
		// 打印请求路径
		String path = request.getPath().pathWithinApplication().value();
		// 忽略 endpoint 请求
		String endpointBasePath = endpointProperties.getBasePath();
		if (StringUtil.isNotBlank(endpointBasePath) && (path.startsWith(endpointBasePath))) {
			return chain.filter(exchange);
		}
		// 开始时间
		long startNanoTime = System.nanoTime();
		// 请求的 url 信息
		LinkedHashSet<URI> uriSet = exchange.getRequiredAttribute(ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
		URI requestUri = uriSet.stream().findFirst().orElse(request.getURI());
		MultiValueMap<String, String> queryParams = request.getQueryParams();
		String requestUrl = UriComponentsBuilder.fromPath(requestUri.getRawPath()).queryParams(queryParams).build().toUriString();

		// 构建成一条长 日志，避免并发下日志错乱
		StringBuilder beforeReqLog = new StringBuilder(300);
		// 日志参数
		List<Object> beforeReqArgs = new ArrayList<>();
		beforeReqLog.append("\n\n================ Gateway Request Start  ================\n");
		// 打印路由
		beforeReqLog.append("===> {}: {}\n");
		// 参数
		String requestMethod = request.getMethodValue();
		beforeReqArgs.add(requestMethod);
		beforeReqArgs.add(requestUrl);

		// 打印请求头
		HttpHeaders headers = request.getHeaders();
		headers.forEach((headerName, headerValue) -> {
			beforeReqLog.append("===Headers===  {}: {}\n");
			beforeReqArgs.add(headerName);
			beforeReqArgs.add(Joiner.on(",").join(headerValue));
		});

		beforeReqLog.append("================  Gateway Request End  =================\n");
		// 打印执行时间
		log.trace(beforeReqLog.toString(), beforeReqArgs.toArray());
		Map<String, Object> attributes = exchange.getAttributes();
		attributes.put(START_NANO_TIME_KEY, startNanoTime);
		return chain.filter(exchange);
	}

	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}
}
