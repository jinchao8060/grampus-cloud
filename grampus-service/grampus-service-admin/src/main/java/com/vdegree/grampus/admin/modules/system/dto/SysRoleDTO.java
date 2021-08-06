package com.vdegree.grampus.admin.modules.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 系统角色DTO
 *
 * @author Beck
 * @since 2020-12-09 19:50:15
 */
@ApiModel("系统角色")
@Data
public class SysRoleDTO implements Serializable {
	private static final long serialVersionUID = -4592002714978614167L;
	/**
	 * 数据ID
	 */
	@ApiModelProperty("数据ID")
	private Long id;
	/**
	 * 角色名
	 */
	@ApiModelProperty(value = "角色名", required = true)
	private String roleName;
	/**
	 * 角色编号
	 */
	@ApiModelProperty(value = "角色编号", required = true)
	private String roleCode;
	/**
	 * 角色菜单ID列表
	 */
	@ApiModelProperty("角色菜单ID列表，既该角色目前具有哪些权限")
	private List<Long> menuIdList;
	/**
	 * 创建者
	 */
	@ApiModelProperty("创建者")
	private String createBy;
	/**
	 * 创建日期
	 */
	@ApiModelProperty("创建日期")
	private LocalDateTime createDate;
	/**
	 * 更新者
	 */
	@ApiModelProperty("更新者")
	private String updateBy;
	/**
	 * 更新日期
	 */
	@ApiModelProperty("更新日期")
	private LocalDateTime updateDate;
}