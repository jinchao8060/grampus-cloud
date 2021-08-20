package com.oceancloud.grampus.admin.modules.system.service.impl;

import com.oceancloud.grampus.framework.mybatis.service.impl.EnhancedBaseServiceImpl;
import com.oceancloud.grampus.admin.modules.system.dao.LogOperationDao;
import com.oceancloud.grampus.admin.modules.system.entity.LogOperation;
import com.oceancloud.grampus.admin.modules.system.dto.LogOperationDTO;
import com.oceancloud.grampus.admin.modules.system.service.LogOperationService;
import org.springframework.stereotype.Service;

/**
 * 操作日志表(LogOperation) 表服务实现类
 *
 * @author Beck
 * @since 2021-05-31
 */
@Service("logOperationService")
public class LogOperationServiceImpl extends EnhancedBaseServiceImpl<LogOperationDao, LogOperation, LogOperationDTO> implements LogOperationService {

}
