package com.vdegree.grampus.gateway.gray;

import org.springframework.http.HttpHeaders;

import java.util.function.Consumer;

/**
 * GrayHeaderConsumer
 *
 * @author Beck
 * @since 2021-06-30
 */
public class GrayHeaderConsumer implements Consumer<HttpHeaders> {

	@Override
	public void accept(HttpHeaders httpHeaders) {
		if ("1.0.0".equals(httpHeaders.getFirst("Version"))) {
			httpHeaders.remove("Version");
			httpHeaders.add("Version", "1.0.1");
		}
	}
}
