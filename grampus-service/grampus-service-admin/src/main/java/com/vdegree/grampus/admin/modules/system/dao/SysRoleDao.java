package com.vdegree.grampus.admin.modules.system.dao;

import com.vdegree.grampus.admin.modules.system.entity.SysRole;
import com.vdegree.grampus.common.mybatis.annotation.MyBatisMapper;
import com.vdegree.grampus.common.mybatis.mapper.BaseMapper;

/**
 * 角色表 数据库访问层
 *
 * @author Beck
 * @since 2020-12-09 19:50:16
 */
@MyBatisMapper
public interface SysRoleDao extends BaseMapper<SysRole> {

}