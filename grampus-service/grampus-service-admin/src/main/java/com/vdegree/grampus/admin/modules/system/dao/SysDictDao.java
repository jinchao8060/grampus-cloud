package com.vdegree.grampus.admin.modules.system.dao;

import com.vdegree.grampus.admin.modules.system.entity.SysDict;
import com.vdegree.grampus.common.mybatis.annotation.MyBatisMapper;
import com.vdegree.grampus.common.mybatis.mapper.BaseMapper;

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