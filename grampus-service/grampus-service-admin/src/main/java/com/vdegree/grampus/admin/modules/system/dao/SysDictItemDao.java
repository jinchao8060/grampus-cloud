package com.vdegree.grampus.admin.modules.system.dao;

import com.vdegree.grampus.admin.modules.system.entity.SysDictItem;
import com.vdegree.grampus.common.mybatis.annotation.MyBatisMapper;
import com.vdegree.grampus.common.mybatis.mapper.BaseMapper;

/**
 * 字典数据表 数据库访问层
 *
 * @author Beck
 * @since 2020-12-09 19:48:31
 */
@MyBatisMapper
public interface SysDictItemDao extends BaseMapper<SysDictItem> {

}