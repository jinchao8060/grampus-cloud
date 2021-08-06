package com.vdegree.grampus.admin.modules.system.dao;

import com.vdegree.grampus.common.mybatis.annotation.MyBatisMapper;
import com.vdegree.grampus.common.mybatis.mapper.BaseMapper;
import com.vdegree.grampus.admin.modules.system.entity.SysLanguage;

/**
 * 系统语言表 表数据库访问层
 *
 * @author Beck
 * @since 2021-06-08 10:42:15
 */
@MyBatisMapper
public interface SysLanguageDao extends BaseMapper<SysLanguage> {

}
