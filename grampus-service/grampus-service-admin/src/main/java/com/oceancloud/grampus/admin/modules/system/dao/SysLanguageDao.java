package com.oceancloud.grampus.admin.modules.system.dao;

import com.oceancloud.grampus.framework.mybatis.annotation.MyBatisMapper;
import com.oceancloud.grampus.framework.mybatis.mapper.BaseMapper;
import com.oceancloud.grampus.admin.modules.system.entity.SysLanguage;

/**
 * 系统语言表 表数据库访问层
 *
 * @author Beck
 * @since 2021-06-08
 */
@MyBatisMapper
public interface SysLanguageDao extends BaseMapper<SysLanguage> {

}
