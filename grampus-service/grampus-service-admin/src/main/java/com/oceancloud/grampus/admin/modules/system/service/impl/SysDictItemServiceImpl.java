package com.oceancloud.grampus.admin.modules.system.service.impl;

import com.oceancloud.grampus.admin.modules.system.dao.SysDictDao;
import com.oceancloud.grampus.admin.modules.system.dto.SysDictItemDTO;
import com.oceancloud.grampus.admin.modules.system.dao.SysDictItemDao;
import com.oceancloud.grampus.admin.modules.system.entity.SysDict;
import com.oceancloud.grampus.admin.modules.system.entity.SysDictItem;
import com.oceancloud.grampus.admin.modules.system.service.SysDictItemService;
import com.oceancloud.grampus.framework.core.utils.BeanUtil;
import com.oceancloud.grampus.framework.mybatis.service.impl.EnhancedBaseServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 字典数据表 服务实现类
 *
 * @author Beck
 * @since 2020-12-09 19:48:32
 */
@AllArgsConstructor
@Service("sysDictItemService")
public class SysDictItemServiceImpl extends EnhancedBaseServiceImpl<SysDictItemDao, SysDictItem, SysDictItemDTO> implements SysDictItemService {

	private final SysDictDao sysDictDao;

	@Override
	public void save(SysDictItemDTO dto) {
		SysDict sysDict = sysDictDao.selectByPrimaryKey(dto.getDictId());
		SysDictItem entity = BeanUtil.copy(dto, SysDictItem.class);
		entity.setDictType(sysDict.getDictType());
		this.insert(entity);
		BeanUtil.copy(entity, dto);
	}
}