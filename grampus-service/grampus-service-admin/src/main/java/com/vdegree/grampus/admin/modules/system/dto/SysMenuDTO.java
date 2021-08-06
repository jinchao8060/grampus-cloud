package com.vdegree.grampus.admin.modules.system.dto;

import com.vdegree.grampus.common.core.utils.tree.TreeNode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 系统菜单DTO
 *
 * @author Beck
 * @since 2021-04-01
 */
@Data
@ApiModel("系统菜单")
public class SysMenuDTO extends TreeNode<SysMenuDTO> {
	private static final long serialVersionUID = 5330634077811336459L;
	/**
	 * 数据ID
	 */
	@ApiModelProperty("数据ID")
	private Long id;
	/**
	 * 父级菜单ID
	 */
	@ApiModelProperty(value = "父菜单ID", example = "顶级结点则不传或传入0")
	private Long parentId;
	/**
	 * 菜单类型(0菜单 1按钮)
	 */
	@ApiModelProperty(value = "菜单类型(0菜单 1按钮)", required = true)
	private Integer type;
	/**
	 * 菜单名
	 */
	@ApiModelProperty(value = "菜单名", required = true)
	private String menuName;
	/**
	 * 权限标识
	 */
	@ApiModelProperty(value = "权限标识", example = "只有按钮类型才需要传入")
	private String permission;
	/**
	 * 菜单路径
	 */
	@ApiModelProperty(value = "菜单路径", example = "只有菜单类型才需要传入")
	private String path;
	/**
	 * 图标
	 */
	@ApiModelProperty("图标")
	private String icon;
	/**
	 * 排序
	 */
	@ApiModelProperty("排序")
	private Integer sort;
}