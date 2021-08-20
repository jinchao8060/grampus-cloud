package com.oceancloud.grampus.admin.modules.system.service.impl;

import com.oceancloud.grampus.admin.modules.system.dto.SysRoleDTO;
import com.oceancloud.grampus.admin.modules.system.dao.SysRoleDao;
import com.oceancloud.grampus.admin.modules.system.entity.SysRole;
import com.oceancloud.grampus.admin.modules.security.redis.SystemRolePermRedis;
import com.oceancloud.grampus.admin.modules.system.service.SysRoleMenuService;
import com.oceancloud.grampus.admin.modules.system.service.SysRoleService;
import com.oceancloud.grampus.framework.mybatis.service.impl.EnhancedBaseServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;

/**
 * 角色表 服务实现类
 *
 * @author Beck
 * @since 2020-12-09
 */
@AllArgsConstructor
@Service("sysRoleService")
public class SysRoleServiceImpl extends EnhancedBaseServiceImpl<SysRoleDao, SysRole, SysRoleDTO> implements SysRoleService {

	private final SysRoleMenuService sysRoleMenuService;
	private final SystemRolePermRedis systemRolePermRedis;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(SysRoleDTO dto) {
		super.save(dto);
		sysRoleMenuService.saveOrUpdate(dto.getId(), dto.getMenuIdList());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void modifyById(SysRoleDTO dto) {
		super.modifyById(dto);
		sysRoleMenuService.saveOrUpdate(dto.getId(), dto.getMenuIdList());
		systemRolePermRedis.removeSystemRolePerms(dto.getId());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteBatchIds(Collection<? extends Serializable> idList) {
		super.deleteBatchIds(idList);
		sysRoleMenuService.deleteByRoleIds(idList);
		idList.forEach(roleId -> systemRolePermRedis.removeSystemRolePerms((Long) roleId));
	}
}