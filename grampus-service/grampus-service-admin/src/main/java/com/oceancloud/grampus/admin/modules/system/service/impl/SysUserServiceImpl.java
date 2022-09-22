package com.oceancloud.grampus.admin.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.oceancloud.grampus.admin.modules.system.dao.SysUserDao;
import com.oceancloud.grampus.admin.modules.system.dto.SysUserDTO;
import com.oceancloud.grampus.admin.modules.system.entity.SysUser;
import com.oceancloud.grampus.admin.modules.system.enums.SuperAdminEnum;
import com.oceancloud.grampus.admin.modules.system.service.SysUserRoleService;
import com.oceancloud.grampus.admin.modules.system.service.SysUserService;
import com.oceancloud.grampus.auth.modules.system.client.feign.RemoteSystemUserDetailsClient;
import com.oceancloud.grampus.framework.core.utils.BeanUtil;
import com.oceancloud.grampus.framework.core.utils.CollectionUtil;
import com.oceancloud.grampus.framework.core.utils.StringUtil;
import com.oceancloud.grampus.framework.mybatis.enums.DelFlagEnum;
import com.oceancloud.grampus.framework.mybatis.service.impl.EnhancedBaseServiceImpl;
import com.oceancloud.grampus.framework.oauth2.modules.system.utils.SystemSecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户表 服务实现类
 *
 * @author Beck
 * @since 2020-12-09
 */
@AllArgsConstructor
@Service("sysUserService")
public class SysUserServiceImpl extends EnhancedBaseServiceImpl<SysUserDao, SysUser, SysUserDTO> implements SysUserService {

	private final PasswordEncoder passwordEncoder;
	private final SysUserRoleService sysUserRoleService;
	private final RemoteSystemUserDetailsClient remoteSystemUserDetailsClient;

	@Override
	public SysUser getSysUserByUserNo(String userNo) {
//		SysUser entity = new SysUser();
//		entity.setUserNo(userNo);
//		entity.setDelFlag(DelFlagEnum.NORMAL.getValue());

		LambdaQueryWrapper<SysUser> wrapper = Wrappers.<SysUser>lambdaQuery()
				.eq(SysUser::getUserNo, userNo)
				.eq(SysUser::getDelFlag, DelFlagEnum.NORMAL.getValue());
		return baseMapper.selectOne(wrapper);
	}

	@Override
	public void updatePassword(Long userId, String newPassword) {
		SysUser sysUser = getById(userId);
		// 超管才能修改超管
		if (SuperAdminEnum.TRUE.getValue().equals(sysUser.getSuperAdmin())
				&& !SuperAdminEnum.TRUE.getValue().equals(SystemSecurityUtils.getUserDetails().getSuperAdmin())) {
			return;
		}
		SysUser entity = new SysUser();
		entity.setId(userId);
		entity.setPassword(StringUtil.isNotBlank(newPassword) ? passwordEncoder.encode(newPassword) : null);
		baseMapper.updateById(entity);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveOne(SysUserDTO dto) {
		SysUser entity = BeanUtil.copy(dto, SysUser.class);
		String plainPwd = entity.getPassword();
		// 新建账号支持空密码
		if (StringUtil.isNotBlank(plainPwd)) {
			entity.setPassword(passwordEncoder.encode(plainPwd));
		}
		entity.setSuperAdmin(SuperAdminEnum.FALSE.getValue());
		baseMapper.insert(entity);
		sysUserRoleService.saveOrUpdate(entity.getId(), dto.getRoleIdList());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void modifyById(SysUserDTO dto) {
		SysUser entity = BeanUtil.copy(dto, SysUser.class);
		SysUser sysUser = getById(entity.getId());
		// 超管才能修改超管
		if (SuperAdminEnum.TRUE.getValue().equals(sysUser.getSuperAdmin())
				&& !SuperAdminEnum.TRUE.getValue().equals(SystemSecurityUtils.getUserDetails().getSuperAdmin())) {
			return;
		}
		String userNo = sysUser.getUserNo();
		String plainPwd = entity.getPassword();
		// 输入空密码则不进行更改
		entity.setPassword(StringUtil.isNotBlank(plainPwd) ? passwordEncoder.encode(plainPwd) : null);
		entity.setSuperAdmin(null);
		updateById(entity);
		if (CollectionUtil.isNotEmpty(dto.getRoleIdList())) {
			sysUserRoleService.saveOrUpdate(entity.getId(), dto.getRoleIdList());
		}
		remoteSystemUserDetailsClient.removeSystemUserDetails(userNo);
	}
}