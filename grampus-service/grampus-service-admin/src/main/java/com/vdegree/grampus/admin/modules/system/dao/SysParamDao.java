package com.vdegree.grampus.admin.modules.system.dao;

import com.vdegree.grampus.admin.modules.system.entity.SysParam;
import com.vdegree.grampus.common.mybatis.annotation.MyBatisMapper;
import com.vdegree.grampus.common.mybatis.mapper.BaseMapper;

/**
 * 公共参数表 数据库访问层
 *
 * @author Beck
 * @since 2020-12-09 19:49:54
 */
@MyBatisMapper
public interface SysParamDao extends BaseMapper<SysParam> {

}