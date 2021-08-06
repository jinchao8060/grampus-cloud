package com.vdegree.grampus.admin.modules.system.dao;

import com.vdegree.grampus.admin.modules.system.entity.SysUserRole;
import com.vdegree.grampus.common.mybatis.annotation.MyBatisMapper;
import com.vdegree.grampus.common.mybatis.mapper.BaseMapper;

/**
 * 用户角色关联表 数据库访问层
 *
 * @author Beck
 * @since 2020-12-09 19:51:17
 */
@MyBatisMapper
public interface SysUserRoleDao extends BaseMapper<SysUserRole> {

}