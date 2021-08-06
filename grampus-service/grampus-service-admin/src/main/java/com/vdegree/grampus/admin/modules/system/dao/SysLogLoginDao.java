package com.vdegree.grampus.admin.modules.system.dao;

import com.vdegree.grampus.admin.modules.system.entity.SysLogLogin;
import com.vdegree.grampus.common.mybatis.annotation.MyBatisMapper;
import com.vdegree.grampus.common.mybatis.mapper.BaseMapper;

/**
 * 用户登陆日志表 数据库访问层
 *
 * @author Beck
 * @since 2020-12-09 19:48:59
 */
@MyBatisMapper
public interface SysLogLoginDao extends BaseMapper<SysLogLogin> {

}