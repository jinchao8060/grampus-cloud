package com.oceancloud.grampus.gateway.logger;

import com.google.common.base.Joiner;
import com.oceancloud.grampus.framework.core.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * gateway 请求日志
 *
 * @author Beck
 * @since 2021-06-10
 */
@Slf4j
@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
public class GlobalResponseLogFilter implements GlobalFilter, Ordered {
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
		return chain.filter(exchange).then(
				Mono.fromRunnable(() -> {
					// 请求信息
					MultiValueMap<String, String> queryParams = request.getQueryParams();
					String requestUrl = UriComponentsBuilder.fromPath(path).queryParams(queryParams).build().toUriString();
					// 请求开始时间
					Long startNanoTime = exchange.getAttribute(GlobalRequestLogFilter.START_NANO_TIME_KEY);

					// 构建成一条长 日志，避免并发下日志错乱
					StringBuilder responseLog = new StringBuilder(300);
					// 日志参数
					List<Object> responseArgs = new ArrayList<>();
					responseLog.append("\n\n================ Gateway Response Start  ================\n");
					ServerHttpResponse response = exchange.getResponse();
					// 打印路由 200 get: /api/xxx/xxx
					responseLog.append("<=== {} {}: {} ({})\n");
					// 参数
					String requestMethod = request.getMethodValue();
					responseArgs.add(response.getStatusCode().value());
					responseArgs.add(requestMethod);
					responseArgs.add(requestUrl);
					// 请求耗时计算
					if (startNanoTime != null) {
						String tookTime = timeFormat(System.nanoTime() - startNanoTime);
						responseArgs.add(tookTime);
					} else {
						responseArgs.add("unkown");
					}
					// 打印请求头
					HttpHeaders headers = response.getHeaders();
					headers.forEach((headerName, headerValue) -> {
						responseLog.append("===Headers===  {}: {}\n");
						responseArgs.add(headerName);
						responseArgs.add(Joiner.on(",").join(headerValue));
					});

					responseLog.append("================  Gateway Response End  =================\n");
					// 打印执行时间
					log.trace(responseLog.toString(), responseArgs.toArray());
				})
		);
	}

	@Override
	public int getOrder() {
		return Ordered.HIGHEST_PRECEDENCE;
	}

	private String timeFormat(long nanos) {
		if (nanos < 1L) {
			return "0ms";
		} else {
			double millis = (double) nanos / 1000000.0D;
			return millis > 1000.0D ? String.format("%.3fs", millis / 1000.0D) : String.format("%.3fms", millis);
		}
	}
}
