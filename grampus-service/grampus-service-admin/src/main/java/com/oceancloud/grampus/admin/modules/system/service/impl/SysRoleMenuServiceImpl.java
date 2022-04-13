package com.oceancloud.grampus.admin.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.oceancloud.grampus.framework.mybatis.service.impl.BaseServiceImpl;
import com.oceancloud.grampus.admin.modules.system.dao.SysRoleMenuDao;
import com.oceancloud.grampus.admin.modules.system.entity.SysRoleMenu;
import com.oceancloud.grampus.admin.modules.system.service.SysRoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色菜单表 服务实现类
 *
 * @author Beck
 * @since 2020-12-09
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl extends BaseServiceImpl<SysRoleMenuDao, SysRoleMenu> implements SysRoleMenuService {

	@Override
	public List<Long> getMenuIdList(Long roleId) {
//		List<SysRoleMenu> list = baseMapper.selectByExample(Example.builder(SysRoleMenu.class)
//				.select("menuId")
//				.where(Sqls.custom().andEqualTo("roleId", roleId))
//				.build());

		LambdaQueryWrapper<SysRoleMenu> wrapper = Wrappers.<SysRoleMenu>lambdaQuery()
				.select(SysRoleMenu::getMenuId)
				.eq(SysRoleMenu::getRoleId, roleId);

		List<SysRoleMenu> list = baseMapper.selectList(wrapper);

		return list.stream().map(SysRoleMenu::getMenuId).distinct().collect(Collectors.toList());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
//		baseMapper.deleteByExample(Example.builder(SysRoleMenu.class)
//				.where(Sqls.custom().andEqualTo("roleId", roleId))
//				.build());
//		menuIdList.forEach(menuId -> {
//			SysRoleMenu entity = new SysRoleMenu();
//			entity.setRoleId(roleId);
//			entity.setMenuId(menuId);
//			baseMapper.insertSelective(entity);
//		});

		baseMapper.delete(Wrappers.<SysRoleMenu>lambdaQuery()
				.eq(SysRoleMenu::getRoleId, roleId));

		menuIdList.forEach(menuId -> {
			SysRoleMenu entity = new SysRoleMenu();
			entity.setRoleId(roleId);
			entity.setMenuId(menuId);
			baseMapper.insert(entity);
		});
	}

	@Override
	public void deleteByRoleIds(Collection<? extends Serializable> roleIds) {
//		baseMapper.deleteByExample(Example.builder(SysRoleMenu.class)
//				.where(Sqls.custom().andIn("roleId", roleIds))
//				.build());

		LambdaQueryWrapper<SysRoleMenu> wrapper = Wrappers.<SysRoleMenu>lambdaQuery()
				.in(SysRoleMenu::getRoleId, roleIds);

		baseMapper.delete(wrapper);
	}
}