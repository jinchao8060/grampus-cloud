package com.vdegree.grampus.admin.modules.system.service;

import com.vdegree.grampus.common.mybatis.service.EnhancedBaseService;
import com.vdegree.grampus.admin.modules.system.entity.SysLanguage;
import com.vdegree.grampus.admin.modules.system.dto.SysLanguageDTO;

import java.util.List;
import java.util.Map;

/**
 * 系统语言表 表服务接口
 *
 * @author Beck
 * @since 2021-06-08 10:42:16
 */
public interface SysLanguageService extends EnhancedBaseService<SysLanguage, SysLanguageDTO> {

	/**
	 * 获取多语言字段值
	 *
	 * @param tableName 表名
	 * @param tableId   表ID
	 * @param fieldName 字段名
	 * @param language  语言标识
	 * @return 字段值
	 */
	String convertFieldValue(String tableName, Long tableId, String fieldName, String language);

	/**
	 * 获取多语言字段值
	 *
	 * @param tableName 表名
	 * @param tableIds  表ID
	 * @param fieldName 字段名
	 * @param language  语言标识
	 * @return key表ID value字段值
	 */
	Map<Long, String> convertFieldValue(String tableName, List<Long> tableIds, String fieldName, String language);
}
