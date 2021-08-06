package com.vdegree.grampus.admin.modules.system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 系统用户启用状态枚举
 *
 * @author Beck
 * @since 2020-12-15
 */
@AllArgsConstructor
public enum SysUserEnabledEnum {

	/**
	 * 禁用
	 */
	DISABLED(0),
	/**
	 * 启用
	 */
	ENABLED(1);

	@Getter
	private final Integer value;
}
