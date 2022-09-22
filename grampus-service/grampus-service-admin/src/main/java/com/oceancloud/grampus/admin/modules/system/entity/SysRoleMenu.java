package com.oceancloud.grampus.admin.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.oceancloud.grampus.framework.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色菜单表 实体类
 *
 * @author Beck
 * @since 2020-12-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role_menu")
public class SysRoleMenu extends BaseEntity {
	private static final long serialVersionUID = 733788304755924267L;
	/**
	 * 角色ID
	 */
	private Long roleId;
	/**
	 * 菜单ID
	 */
	private Long menuId;
}