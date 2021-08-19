package com.oceancloud.grampus.admin.modules.system.enums;

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
	CLIENT("client"),
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
