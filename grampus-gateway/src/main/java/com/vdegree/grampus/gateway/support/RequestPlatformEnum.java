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
	 * API
	 */
	API("api"),
	/**
	 * ADMIN
	 */
	ADMIN("admin");

	@Getter
	private String platform;

	RequestPlatformEnum(String platform) {
		this.platform = platform;
	}
}
