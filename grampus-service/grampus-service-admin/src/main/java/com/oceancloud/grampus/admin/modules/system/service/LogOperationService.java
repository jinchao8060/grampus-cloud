package com.oceancloud.grampus.admin.modules.system.service;

import com.oceancloud.grampus.admin.modules.system.dto.LogOperationDTO;
import com.oceancloud.grampus.admin.modules.system.entity.LogOperation;
import com.oceancloud.grampus.framework.mybatis.service.EnhancedBaseService;

/**
 * 操作日志表(LogOperation) 表服务接口
 *
 * @author Beck
 * @since 2021-05-31
 */
public interface LogOperationService extends EnhancedBaseService<LogOperation, LogOperationDTO> {

}
