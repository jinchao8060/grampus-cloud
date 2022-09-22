package com.oceancloud.grampus.admin.modules.system.dao;

import com.oceancloud.grampus.admin.modules.system.entity.SysRole;
import com.oceancloud.grampus.framework.mybatis.annotation.MyBatisMapper;
import com.oceancloud.grampus.framework.mybatis.mapper.BaseMapper;

/**
 * 角色表 数据库访问层
 *
 * @author Beck
 * @since 2020-12-09
 */
@MyBatisMapper
public interface SysRoleDao extends BaseMapper<SysRole> {

}