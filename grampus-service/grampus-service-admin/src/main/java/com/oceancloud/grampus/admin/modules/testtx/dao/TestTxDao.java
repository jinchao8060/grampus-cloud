package com.oceancloud.grampus.admin.modules.testtx.dao;

import com.oceancloud.grampus.framework.mybatis.annotation.MyBatisMapper;
import com.oceancloud.grampus.framework.mybatis.mapper.BaseMapper;
import com.oceancloud.grampus.admin.modules.testtx.entity.TestTx;

/**
 * (TestTx) 表数据库访问层
 *
 * @author Beck
 * @since 2021-06-21 17:13:17
 */
@MyBatisMapper
public interface TestTxDao extends BaseMapper<TestTx> {

}
