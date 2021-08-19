package com.oceancloud.grampus.admin.modules.system.dao;

import com.oceancloud.grampus.admin.modules.system.entity.SysUserRole;
import com.oceancloud.grampus.framework.mybatis.annotation.MyBatisMapper;
import com.oceancloud.grampus.framework.mybatis.mapper.BaseMapper;

/**
 * 用户角色关联表 数据库访问层
 *
 * @author Beck
 * @since 2020-12-09 19:51:17
 */
@MyBatisMapper
public interface SysUserRoleDao extends BaseMapper<SysUserRole> {

}