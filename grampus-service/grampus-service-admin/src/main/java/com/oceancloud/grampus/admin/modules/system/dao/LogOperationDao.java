package com.oceancloud.grampus.admin.modules.system.dao;

import com.oceancloud.grampus.framework.mybatis.annotation.MyBatisMapper;
import com.oceancloud.grampus.framework.mybatis.mapper.BaseMapper;
import com.oceancloud.grampus.admin.modules.system.entity.LogOperation;

/**
 * 操作日志表(LogOperation) 表数据库访问层
 *
 * @author Beck
 * @since 2021-05-31 16:43:11
 */
@MyBatisMapper
public interface LogOperationDao extends BaseMapper<LogOperation> {

}
