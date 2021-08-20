package com.oceancloud.grampus.admin.modules.system.service;

import com.oceancloud.grampus.admin.modules.system.dto.SysMenuDTO;
import com.oceancloud.grampus.framework.oauth2.modules.system.users.SystemUserDetails;
import com.oceancloud.grampus.admin.modules.system.entity.SysMenu;
import com.oceancloud.grampus.framework.mybatis.service.EnhancedBaseService;

import java.util.List;
import java.util.Set;

/**
 * 菜单表 服务接口
 *
 * @author Beck
 * @since 2020-12-09
 */
public interface SysMenuService extends EnhancedBaseService<SysMenu, SysMenuDTO> {

	/**
	 * 菜单列表（全部）
	 *
	 * @param type 菜单类型
	 */
	List<SysMenuDTO> getMenuList(Integer type);

	/**
	 * 用户菜单列表
	 *
	 * @param userDetail 用户信息
	 * @param type       菜单类型
	 */
	List<SysMenuDTO> getUserMenuList(SystemUserDetails userDetail, Integer type);

	/**
	 * 用户菜单导航
	 *
	 * @param userDetail 用户信息
	 */
	List<SysMenuDTO> getUserMenuNavList(SystemUserDetails userDetail);

	/**
	 * 获取用户权限标识
	 *
	 * @param userDetail 用户信息
	 */
	Set<String> getUserPermissions(SystemUserDetails userDetail);

	/**
	 * 根据父菜单，查询子菜单
	 *
	 * @param pid 父菜单ID
	 */
	List<SysMenuDTO> getListByPid(Long pid);
}