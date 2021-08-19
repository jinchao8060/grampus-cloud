package com.oceancloud.grampus.admin.modules.system.entity;

import com.oceancloud.grampus.framework.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;

/**
 * 角色菜单表 实体类
 *
 * @author Beck
 * @since 2020-12-09 19:50:42
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "sys_role_menu")
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