package com.vdegree.grampus.auth.modules.security.users;

import com.vdegree.grampus.admin.modules.security.client.dto.SystemUserDTO;
import com.vdegree.grampus.admin.modules.security.client.feign.RemoteSystemRoleClient;
import com.vdegree.grampus.admin.modules.security.client.feign.RemoteSystemUserClient;
import com.vdegree.grampus.auth.modules.security.redis.SystemUserDetailsRedis;
import com.vdegree.grampus.auth.modules.system.enums.SuperAdminEnum;
import com.vdegree.grampus.auth.modules.system.enums.SysUserEnabledEnum;
import com.vdegree.grampus.common.auth.modules.system.users.SystemUserDetails;
import com.vdegree.grampus.common.core.utils.BeanUtil;
import com.vdegree.grampus.common.core.utils.StringUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统用户详情
 *
 * @author Beck
 * @since 2020-12-15
 */
@AllArgsConstructor
@Service
public class SystemUserDetailsService implements UserDetailsService {
	private final RemoteSystemUserClient remoteSystemUserClient;
	private final RemoteSystemRoleClient remoteSystemRoleClient;
	private final SystemUserDetailsRedis systemUserDetailsRedis;

	@Override
	public UserDetails loadUserByUsername(String userNo) throws UsernameNotFoundException {
		// 查询用户详情
		SystemUserDetails userDetails = systemUserDetailsRedis.getSystemUserDetails(userNo);
		if (userDetails != null && StringUtil.isNotBlank(userDetails.getUserNo())) {
			// 查询用户权限标识
			if (SuperAdminEnum.TRUE.getValue().equals(userDetails.getSuperAdmin())) {
				// TODO 考虑是否需要优化超管的权限标识获取
				userDetails.setPermissions(remoteSystemRoleClient.getAllPermissions().getData());
			} else {
				String permissions = remoteSystemRoleClient.getPermissionsByRoleIds(userDetails.getRoleIds()).getData();
				userDetails.setPermissions(permissions);
			}
			return userDetails;
		}
		SystemUserDTO user = remoteSystemUserClient.getSysUserByUserNo(userNo).getData();

		// 用户不存在
		if (user == null) {
//			throw new UserNotFoundException();
			throw new UsernameNotFoundException("System user not exists.");
		}

		// 用户被禁用
		if (SysUserEnabledEnum.DISABLED.getValue().equals(user.getEnabled())) {
//			throw new UserDisabledException();
			throw new DisabledException("System user is disabled.");
		}

		// 缓存系统用户详情
		userDetails = buildUserDetails(user);
		systemUserDetailsRedis.saveSystemUserDetails(userDetails);
		return userDetails;
	}

	public SystemUserDetails buildUserDetails(SystemUserDTO user) {
		SystemUserDetails systemUserDetails = BeanUtil.copyWithConvert(user, SystemUserDetails.class);
		List<Long> roleIds = remoteSystemRoleClient.getRoleIds(user.getId()).getData();
		boolean isSuperAdmin = SuperAdminEnum.TRUE.getValue().equals(systemUserDetails.getSuperAdmin());
		systemUserDetails.setRoleIds(roleIds);
		if (Boolean.TRUE.equals(isSuperAdmin)) {
			systemUserDetails.setPermissions(remoteSystemRoleClient.getAllPermissions().getData());
		} else {
			systemUserDetails.setPermissions(remoteSystemRoleClient.getPermissions(user.getId()).getData());
		}
		return systemUserDetails;
	}
}
