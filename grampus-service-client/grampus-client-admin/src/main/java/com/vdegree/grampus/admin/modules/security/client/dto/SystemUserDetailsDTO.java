package com.vdegree.grampus.admin.modules.security.client.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * SystemUserDetailsDTO
 *
 * @author Beck
 * @since 2021-08-05
 */
@Data
public class SystemUserDetailsDTO implements Serializable {
	private static final long serialVersionUID = -5869096064977643588L;

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
	 * 密码
	 */
	private String password;

	/**
	 * 性别
	 */
	private Integer gender;

	/**
	 * 所属角色ID
	 */
	private List<Long> roleIds;

	/**
	 * 所属部门ID
	 */
	private Long deptId;

	/**
	 * 是否超级管理员(0普通 1超管)
	 */
	private Integer superAdmin;

	/**
	 * 是否启用(0停用 1启用)
	 */
	private Integer enabled;

	/**
	 * 拥有权限
	 */
	private String permissions;
}
