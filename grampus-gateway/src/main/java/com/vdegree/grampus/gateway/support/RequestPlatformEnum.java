package com.vdegree.grampus.gateway.support;

import lombok.Getter;

/**
 * 请求平台枚举
 *
 * @author Beck
 * @since 2021-06-10
 */
public enum RequestPlatformEnum {
	/**
	 * PLATFORM_KEY
	 */
	PLATFORM_KEY("platform"),
	/**
	 * API
	 */
	CLIENT("client"),
	/**
	 * ADMIN
	 */
	ADMIN("admin");

	@Getter
	private String platform;

	@Getter
	private String platformKey;

	RequestPlatformEnum(String platform) {
		this.platform = platform;
	}
}
