package com.oceancloud.grampus.admin.modules.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.oceancloud.grampus.admin.modules.system.dao.SysUserRoleDao;
import com.oceancloud.grampus.admin.modules.system.entity.SysUserRole;
import com.oceancloud.grampus.admin.modules.system.service.SysUserRoleService;
import com.oceancloud.grampus.framework.mybatis.service.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户角色关联表 服务实现类
 *
 * @author Beck
 * @since 2020-12-09
 */
@AllArgsConstructor
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRoleDao, SysUserRole> implements SysUserRoleService {

	private final SysUserRoleDao sysUserRoleDao;

	@Override
	public List<SysUserRole> getUserRole(Long userId) {
//		return sysUserRoleDao.selectByExample(Example.builder(SysUserRole.class)
//				.where(Sqls.custom().andEqualTo("userId", userId)).build());

//		return sysUserRoleDao.selectByExample(Example.builder(SysUserRole.class)
//				.where(WeekendSqls.<SysUserRole>custom()
//						.andEqualTo(SysUserRole::getUserId, userId)).build());
		return sysUserRoleDao.selectList(Wrappers.<SysUserRole>lambdaQuery()
				.eq(SysUserRole::getUserId, userId));
	}

	@Override
	public List<Long> getRoleIdList(Long userId) {
//		List<SysUserRole> result = sysUserRoleDao.selectByExample(Example.builder(SysUserRole.class)
//				.select("roleId")
//				.where(WeekendSqls.<SysUserRole>custom()
//						.andEqualTo(SysUserRole::getUserId, userId)).build());
		List<SysUserRole> result = sysUserRoleDao.selectList(Wrappers.<SysUserRole>lambdaQuery()
				.select(SysUserRole::getRoleId)
				.eq(SysUserRole::getUserId, userId));
		return result.stream().map(SysUserRole::getRoleId).distinct().collect(Collectors.toList());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveOrUpdate(Long userId, List<Long> roleIdList) {
//		baseMapper.deleteByExample(Example.builder(SysUserRole.class)
//				.where(Sqls.custom().andEqualTo("userId", userId))
//				.build());
//		roleIdList.forEach(roleId -> {
//			SysUserRole entity = new SysUserRole();
//			entity.setUserId(userId);
//			entity.setRoleId(roleId);
//			baseMapper.insertSelective(entity);
//		});
		baseMapper.delete(Wrappers.<SysUserRole>lambdaQuery()
				.eq(SysUserRole::getUserId, userId));
		roleIdList.forEach(roleId -> {
			SysUserRole entity = new SysUserRole();
			entity.setUserId(userId);
			entity.setRoleId(roleId);
			baseMapper.insert(entity);
		});
	}
}