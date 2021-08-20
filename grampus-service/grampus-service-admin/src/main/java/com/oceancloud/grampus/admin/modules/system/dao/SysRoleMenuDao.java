package com.oceancloud.grampus.admin.modules.system.dao;

import com.oceancloud.grampus.admin.modules.system.entity.SysRoleMenu;
import com.oceancloud.grampus.framework.mybatis.annotation.MyBatisMapper;
import com.oceancloud.grampus.framework.mybatis.mapper.BaseMapper;

/**
 * 角色菜单表 数据库访问层
 *
 * @author Beck
 * @since 2020-12-09
 */
@MyBatisMapper
public interface SysRoleMenuDao extends BaseMapper<SysRoleMenu> {

}