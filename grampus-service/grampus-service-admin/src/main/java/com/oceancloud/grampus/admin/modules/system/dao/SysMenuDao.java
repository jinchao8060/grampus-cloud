package com.oceancloud.grampus.admin.modules.system.dao;

import com.oceancloud.grampus.admin.modules.system.entity.SysMenu;
import com.oceancloud.grampus.framework.mybatis.annotation.MyBatisMapper;
import com.oceancloud.grampus.framework.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单表 数据库访问层
 *
 * @author Beck
 * @since 2020-12-09
 */
@MyBatisMapper
public interface SysMenuDao extends BaseMapper<SysMenu> {

	/**
	 * 查询用户菜单列表
	 *
	 * @param userId 用户ID
	 * @param type   菜单类型
	 */
	List<SysMenu> queryUserMenuList(@Param("userId") Long userId, @Param("type") Integer type);
}