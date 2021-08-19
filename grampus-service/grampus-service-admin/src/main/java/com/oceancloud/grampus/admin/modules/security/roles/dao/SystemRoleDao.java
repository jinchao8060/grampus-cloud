package com.oceancloud.grampus.admin.modules.security.roles.dao;

import com.oceancloud.grampus.admin.modules.security.roles.dto.SystemRolePermDTO;
import com.oceancloud.grampus.framework.mybatis.annotation.MyBatisMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 系统角色
 *
 * @author Beck
 * @since 2020-12-18
 */
@MyBatisMapper
public interface SystemRoleDao {

	/**
	 * 获取用户所有角色ID
	 *
	 * @param userId 用户ID
	 * @return 角色ID
	 */
	List<Long> getRoleIds(@Param("userId") Long userId);

	/**
	 * 获取角色所有权限标识
	 *
	 * @param roleIds 角色ID
	 * @return 权限标识
	 */
	List<SystemRolePermDTO> getPermissionsByRoleIds(@Param("roleIds") List<Long> roleIds);

	/**
	 * 获取用户所有权限标识
	 *
	 * @param userId 用户ID
	 * @return 权限标识
	 */
	Set<String> getPermissions(@Param("userId") Long userId);

	/**
	 * 获取所有权限标识
	 *
	 * @return 权限标识
	 */
	Set<String> getAllPermissions();
}
