package com.oceancloud.grampus.admin.modules.system.dao;

import com.oceancloud.grampus.admin.modules.system.entity.SysDictItem;
import com.oceancloud.grampus.framework.mybatis.annotation.MyBatisMapper;
import com.oceancloud.grampus.framework.mybatis.mapper.BaseMapper;

/**
 * 字典数据表 数据库访问层
 *
 * @author Beck
 * @since 2020-12-09 19:48:31
 */
@MyBatisMapper
public interface SysDictItemDao extends BaseMapper<SysDictItem> {

}