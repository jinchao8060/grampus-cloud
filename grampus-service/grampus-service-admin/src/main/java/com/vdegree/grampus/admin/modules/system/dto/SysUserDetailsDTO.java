package com.vdegree.grampus.admin.modules.system.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统会员详情DTO
 *
 * @author Beck
 * @since 2021-05-07
 */
@ApiModel("系统角色详情")
@Data
public class SysUserDetailsDTO implements Serializable {
	private static final long serialVersionUID = 6809412036525186257L;
	/**
	 * 用户ID
	 */
	private Long id;
	/**
	 * 员工号
	 */
	private String userNo;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 性别
	 */
	private Integer gender;
	/**
	 * 所属部门ID
	 */
	private Long deptId;
	/**
	 * 是否超级管理员(0普通 1超管)
	 */
	private Integer superAdmin;
	/**
	 * 拥有权限
	 */
	private String permissions;
}
