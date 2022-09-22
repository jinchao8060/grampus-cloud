package com.oceancloud.grampus.admin.modules.system.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * SysRoleQuery
 *
 * @author Beck
 * @since 2021-08-20
 */
@Data
@ApiModel("系统角色")
public class SysRoleQuery implements Serializable {
	private static final long serialVersionUID = -2904937768611925344L;
	/**
	 * 角色名
	 */
	@ApiModelProperty(value = "角色名")
	private String roleName;
	/**
	 * 角色编号
	 */
	@ApiModelProperty(value = "角色编号")
	private String roleCode;
}