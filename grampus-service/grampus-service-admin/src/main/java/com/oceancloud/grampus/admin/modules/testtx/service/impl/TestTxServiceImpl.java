package com.oceancloud.grampus.admin.modules.testtx.service.impl;

import com.oceancloud.grampus.framework.mybatis.service.impl.EnhancedBaseServiceImpl;
import com.oceancloud.grampus.admin.modules.testtx.dao.TestTxDao;
import com.oceancloud.grampus.admin.modules.testtx.entity.TestTx;
import com.oceancloud.grampus.admin.modules.system.client.dto.TestTxDTO;
import com.oceancloud.grampus.admin.modules.testtx.service.TestTxService;
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
