package com.vdegree.grampus.admin.modules.system.service;

import com.vdegree.grampus.common.mybatis.service.BaseService;
import com.vdegree.grampus.admin.modules.system.entity.SysRoleMenu;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 角色菜单表 服务接口
 *
 * @author Beck
 * @since 2020-12-09 19:50:43
 */
public interface SysRoleMenuService extends BaseService<SysRoleMenu> {

	/**
	 * 获取角色菜单ID列表
	 *
	 * @param roleId 角色ID
	 * @return 菜单ID列表
	 */
	List<Long> getMenuIdList(Long roleId);

	/**
	 * 保存或更新角色对应的菜单列表
	 *
	 * @param roleId     角色ID
	 * @param menuIdList 菜单ID列表
	 */
	void saveOrUpdate(Long roleId, List<Long> menuIdList);

	/**
	 * 根据角色ID删除角色对应的菜单列表
	 *
	 * @param roleIds 角色ID
	 */
	void deleteByRoleIds(Collection<? extends Serializable> roleIds);
}