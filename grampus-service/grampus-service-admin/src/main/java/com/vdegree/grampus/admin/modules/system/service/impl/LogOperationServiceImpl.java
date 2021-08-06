package com.vdegree.grampus.admin.modules.system.service.impl;

import com.vdegree.grampus.common.mybatis.service.impl.EnhancedBaseServiceImpl;
import com.vdegree.grampus.admin.modules.system.dao.LogOperationDao;
import com.vdegree.grampus.admin.modules.system.entity.LogOperation;
import com.vdegree.grampus.admin.modules.system.dto.LogOperationDTO;
import com.vdegree.grampus.admin.modules.system.service.LogOperationService;
import org.springframework.stereotype.Service;

/**
 * 操作日志表(LogOperation) 表服务实现类
 *
 * @author Beck
 * @since 2021-05-31 16:43:11
 */
@Service("logOperationService")
public class LogOperationServiceImpl extends EnhancedBaseServiceImpl<LogOperationDao, LogOperation, LogOperationDTO> implements LogOperationService {

}
