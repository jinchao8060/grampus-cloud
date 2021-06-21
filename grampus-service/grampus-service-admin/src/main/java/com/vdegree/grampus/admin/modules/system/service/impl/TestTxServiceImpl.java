package com.vdegree.grampus.admin.modules.system.service.impl;

import com.vdegree.grampus.common.mybatis.service.impl.EnhancedBaseServiceImpl;
import com.vdegree.grampus.admin.modules.system.dao.TestTxDao;
import com.vdegree.grampus.admin.modules.system.entity.TestTx;
import com.vdegree.grampus.admin.modules.system.client.dto.TestTxDTO;
import com.vdegree.grampus.admin.modules.system.service.TestTxService;
import org.springframework.stereotype.Service;

/**
 * (TestTx) 表服务实现类
 *
 * @author Beck
 * @since 2021-06-21 17:13:18
 */
@Service("testTxService")
public class TestTxServiceImpl extends EnhancedBaseServiceImpl<TestTxDao, TestTx, TestTxDTO> implements TestTxService {

}
