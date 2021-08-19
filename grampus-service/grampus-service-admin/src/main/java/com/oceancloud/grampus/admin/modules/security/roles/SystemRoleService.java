package com.oceancloud.grampus.admin.modules.security.roles;

import com.google.common.base.Joiner;
import com.oceancloud.grampus.admin.modules.security.redis.SystemRolePermRedis;
import com.oceancloud.grampus.admin.modules.security.roles.dao.SystemRoleDao;
import com.oceancloud.grampus.admin.modules.security.roles.dto.SystemRolePermDTO;
import com.oceancloud.grampus.framework.core.utils.CollectionUtil;
import com.oceancloud.grampus.framework.core.utils.StringUtil;
import com.oceancloud.grampus.framework.core.utils.chars.StringPool;
import lombok.AllArgsConstructor;
import org.assertj.core.util.Sets;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 系统角色 // TODO 代码优化
 *
 * @author Beck
 * @since 2020-12-15
 */
@Service
@AllArgsConstructor
public class SystemRoleService {

	private final SystemRoleDao systemRoleDao;
	private final SystemRolePermRedis systemRolePermRedis;

	/**
	 * 获取用户所有角色ID
	 *
	 * @param userId 用户ID
	 * @return 角色ID
	 */
	public List<Long> getRoleIds(Long userId) {
		return systemRoleDao.getRoleIds(userId);
	}

	/**
	 * 获取角色所有权限标识
	 *
	 * @param roleIds 角色ID
	 * @return 权限标识
	 */
	public String getPermissionsByRoleIds(List<Long> roleIds) {
		// 缓存中获取角色权限标识
		String perms = systemRolePermRedis.getSystemRolePerms(roleIds);
		if (StringUtil.isNotBlank(perms)) {
			return perms;
		}
		// 缓存为空，数据库中获取角色权限标识
		List<SystemRolePermDTO> permissionList = systemRoleDao.getPermissionsByRoleIds(roleIds);
		if (CollectionUtil.isEmpty(permissionList)) {
			return StringPool.EMPTY;
		}
		// 缓存权限标识
		Set<String> permSet = Sets.newHashSet();
		for (SystemRolePermDTO systemRolePermDTO : permissionList) {
			Set<String> permissionSet = systemRolePermDTO.getPermission();
			String permissions = Joiner.on(StringPool.COMMA).join(permissionSet.stream().filter(perm -> Objects.nonNull(perm)
					&& StringUtil.isNotBlank(perm)).collect(Collectors.toSet()));
			systemRolePermRedis.saveSystemRolePerms(systemRolePermDTO.getRoleId(), permissions);
			permSet.addAll(permissionSet);
		}
		return Joiner.on(StringPool.COMMA).join(permSet.stream().filter(perm -> Objects.nonNull(perm)
				&& StringUtil.isNotBlank(perm)).collect(Collectors.toSet()));
	}

	/**
	 * 获取用户所有权限标识
	 *
	 * @param userId 用户ID
	 * @return 权限标识
	 */
	public String getPermissions(Long userId) {
		Set<String> permissionSet = systemRoleDao.getPermissions(userId);
		return Joiner.on(StringPool.COMMA).join(permissionSet.stream().filter(perm -> Objects.nonNull(perm)
				&& StringUtil.isNotBlank(perm)).collect(Collectors.toSet()));
	}

	/**
	 * 获取所有权限标识
	 *
	 * @return 权限标识
	 */
	public String getAllPermissions() {
		Set<String> permissionSet = systemRoleDao.getAllPermissions();
		return Joiner.on(StringPool.COMMA).join(permissionSet.stream().filter(perm -> Objects.nonNull(perm)
				&& StringUtil.isNotBlank(perm)).collect(Collectors.toSet()));
	}
}
