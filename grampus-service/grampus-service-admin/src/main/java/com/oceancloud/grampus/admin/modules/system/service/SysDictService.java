package com.oceancloud.grampus.admin.modules.system.service;

import com.oceancloud.grampus.admin.modules.system.dto.SysDictDTO;
import com.oceancloud.grampus.admin.modules.system.entity.SysDict;
import com.oceancloud.grampus.framework.mybatis.service.EnhancedBaseService;

import java.util.List;

/**
 * 字典表 服务接口
 *
 * @author Beck
 * @since 2020-12-09
 */
public interface SysDictService extends EnhancedBaseService<SysDict, SysDictDTO> {

	/**
	 * 获取系统字典列表
	 */
	List<SysDict> getSysDictList();
}