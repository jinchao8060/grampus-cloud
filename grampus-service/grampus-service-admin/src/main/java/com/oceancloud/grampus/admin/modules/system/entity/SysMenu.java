package com.oceancloud.grampus.admin.modules.system.entity;

import com.oceancloud.grampus.framework.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tk.mybatis.mapper.annotation.LogicDelete;

import javax.persistence.Table;

/**
 * 菜单表 实体类
 *
 * @author Beck
 * @since 2020-12-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "sys_menu")
public class SysMenu extends BaseEntity {
	private static final long serialVersionUID = 803530310552398145L;
	/**
	 * 父级菜单ID
	 */
	private Long parentId;
	/**
	 * 菜单类型(0菜单 1按钮)
	 */
	private Integer type;
	/**
	 * 菜单名
	 */
	private String menuName;
	/**
	 * 权限标识
	 */
	private String permission;
	/**
	 * 菜单路径
	 */
	private String path;
	/**
	 * 图标
	 */
	private String icon;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 删除标识(0正常 1删除)
	 */
	@LogicDelete
	private Integer delFlag;
}