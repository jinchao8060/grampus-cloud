package com.oceancloud.grampus.auth.modules.system.enums;

import lombok.Getter;

/**
 * 超级管理员枚举
 *
 * @author Beck
 * @since 2021-04-09
 */
public enum SuperAdminEnum {
	/**
	 * 超级管理员
	 */
	TRUE(1),
	/**
	 * 非超级管理员
	 */
	FALSE(0);

	@Getter
	private final Integer value;

	SuperAdminEnum(Integer value) {
		this.value = value;
	}
}