package com.oceancloud.grampus.admin.modules.security.roles.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Set;

/**
 * SystemRolePermissionDTO
 *
 * @author Beck
 * @since 2021-06-09
 */
@Getter
@Setter
@ToString
public class SystemRolePermDTO implements Serializable {
	private static final long serialVersionUID = 3210035152584126389L;
	/**
	 * 角色ID
	 */
	private Long roleId;
	/**
	 * 权限标识
	 */
	private Set<String> permission;
}
