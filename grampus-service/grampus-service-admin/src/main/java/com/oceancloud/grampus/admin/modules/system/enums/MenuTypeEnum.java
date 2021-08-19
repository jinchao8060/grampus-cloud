package com.oceancloud.grampus.admin.modules.system.enums;

import lombok.Getter;

/**
 * 菜单类型枚举
 *
 * @author Beck
 * @since 2021-04-09
 */
public enum MenuTypeEnum {
	/**
	 * 菜单
	 */
	MENU(0),
	/**
	 * 按钮
	 */
	BUTTON(1);

	@Getter
	private int value;

	MenuTypeEnum(int value) {
		this.value = value;
	}
}
