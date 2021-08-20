package com.oceancloud.grampus.admin.modules.system.dao;

import com.oceancloud.grampus.admin.modules.system.entity.SysLogLogin;
import com.oceancloud.grampus.framework.mybatis.annotation.MyBatisMapper;
import com.oceancloud.grampus.framework.mybatis.mapper.BaseMapper;

/**
 * 用户登陆日志表 数据库访问层
 *
 * @author Beck
 * @since 2020-12-09
 */
@MyBatisMapper
public interface SysLogLoginDao extends BaseMapper<SysLogLogin> {

}