package com.oceancloud.grampus.admin.modules.system.dao;

import com.oceancloud.grampus.admin.modules.system.entity.SysDict;
import com.oceancloud.grampus.framework.mybatis.annotation.MyBatisMapper;
import com.oceancloud.grampus.framework.mybatis.mapper.BaseMapper;

import java.util.List;

/**
 * 字典表 数据库访问层
 *
 * @author Beck
 * @since 2020-12-09 19:47:46
 */
@MyBatisMapper
public interface SysDictDao extends BaseMapper<SysDict> {
	/**
	 * 获取系统字典列表
	 */
	List<SysDict> getSysDictList();
}