package com.vdegree.grampus.admin.modules.system.dao;

import com.vdegree.grampus.admin.modules.system.entity.SysDept;
import com.vdegree.grampus.common.mybatis.annotation.MyBatisMapper;
import com.vdegree.grampus.common.mybatis.mapper.BaseMapper;

/**
 * 部门表 数据库访问层
 *
 * @author Beck
 * @since 2020-12-03 20:06:58
 */
@MyBatisMapper
public interface SysDeptDao extends BaseMapper<SysDept> {

}