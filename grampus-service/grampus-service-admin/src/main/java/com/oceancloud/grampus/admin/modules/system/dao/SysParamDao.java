package com.oceancloud.grampus.admin.modules.system.dao;

import com.oceancloud.grampus.admin.modules.system.entity.SysParam;
import com.oceancloud.grampus.framework.mybatis.annotation.MyBatisMapper;
import com.oceancloud.grampus.framework.mybatis.mapper.BaseMapper;

/**
 * 公共参数表 数据库访问层
 *
 * @author Beck
 * @since 2020-12-09 19:49:54
 */
@MyBatisMapper
public interface SysParamDao extends BaseMapper<SysParam> {

}