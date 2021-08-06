package com.vdegree.grampus.admin.modules.system.service;

import com.vdegree.grampus.common.mybatis.service.EnhancedBaseService;
import com.vdegree.grampus.admin.modules.system.entity.LogOperation;
import com.vdegree.grampus.admin.modules.system.dto.LogOperationDTO;

/**
 * 操作日志表(LogOperation) 表服务接口
 *
 * @author Beck
 * @since 2021-05-31 16:43:11
 */
public interface LogOperationService extends EnhancedBaseService<LogOperation, LogOperationDTO> {

}
