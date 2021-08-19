package com.oceancloud.grampus.admin.modules.system.service.impl;

import com.google.common.collect.Maps;
import com.oceancloud.grampus.framework.core.utils.CollectionUtil;
import com.oceancloud.grampus.framework.core.utils.ObjectUtil;
import com.oceancloud.grampus.framework.core.utils.StringUtil;
import com.oceancloud.grampus.framework.mybatis.service.impl.EnhancedBaseServiceImpl;
import com.oceancloud.grampus.admin.modules.system.dao.SysLanguageDao;
import com.oceancloud.grampus.admin.modules.system.entity.SysLanguage;
import com.oceancloud.grampus.admin.modules.system.dto.SysLanguageDTO;
import com.oceancloud.grampus.admin.modules.system.service.SysLanguageService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 系统语言表 表服务实现类
 *
 * @author Beck
 * @since 2021-06-08 10:42:16
 */
@Service("sysLanguageService")
public class SysLanguageServiceImpl extends EnhancedBaseServiceImpl<SysLanguageDao, SysLanguage, SysLanguageDTO> implements SysLanguageService {

	@Override
	public String convertFieldValue(String tableName, Long tableId, String fieldName, String language) {
		SysLanguage entity = baseMapper.selectOneByExample(Example.builder(SysLanguage.class)
				.select("fieldValue")
				.where(WeekendSqls.<SysLanguage>custom()
						.andEqualTo(SysLanguage::getTableName, tableName)
						.andEqualTo(SysLanguage::getTableId, tableId)
						.andEqualTo(SysLanguage::getFieldName, fieldName)
						.andEqualTo(SysLanguage::getLanguage, language))
				.build());
		return ObjectUtil.isNull(entity) ? "" : entity.getFieldValue();
	}

	@Override
	public Map<Long, String> convertFieldValue(String tableName, List<Long> tableIds, String fieldName, String language) {
		List<Long> tableIdList = tableIds.stream().filter(ObjectUtil::isNotNull).distinct().collect(Collectors.toList());
		if (CollectionUtil.isEmpty(tableIdList)) {
			return Maps.newHashMap();
		}
		if (StringUtil.isBlank(language)) {
			language = "zh-CN";
		}
		List<SysLanguage> list = baseMapper.selectByExample(Example.builder(SysLanguage.class)
				.select("tableId", "fieldValue")
				.where(WeekendSqls.<SysLanguage>custom()
						.andEqualTo(SysLanguage::getTableName, tableName)
						.andEqualTo(SysLanguage::getFieldName, fieldName)
						.andEqualTo(SysLanguage::getLanguage, language)
						.andIn(SysLanguage::getTableId, tableIdList)
				).build());
		return list.stream().collect(Collectors.toMap(SysLanguage::getTableId, SysLanguage::getFieldValue));
	}
}
