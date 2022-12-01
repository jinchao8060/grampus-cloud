package com.oceancloud.grampus.admin.modules.system.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.oceancloud.grampus.admin.modules.system.dto.SysDictItemDTO;
import com.oceancloud.grampus.admin.modules.system.entity.SysDictItem;
import com.oceancloud.grampus.admin.modules.system.query.SysDictItemQuery;
import com.oceancloud.grampus.admin.modules.system.service.SysDictItemService;
import com.oceancloud.grampus.framework.core.result.Result;
import com.oceancloud.grampus.framework.core.utils.BeanUtil;
import com.oceancloud.grampus.framework.core.utils.StringUtil;
import com.oceancloud.grampus.framework.log.annotation.RequestLog;
import com.oceancloud.grampus.framework.mybatis.page.PageData;
import com.oceancloud.grampus.framework.mybatis.page.PageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 字典数据项接口
 *
 * @author Beck
 * @since 2021-04-09
 */
@Api(tags = "字典详情管理")
@RestController
@AllArgsConstructor
@RequestMapping("/system/dict/item")
public class SysDictItemController {

	private final SysDictItemService sysDictItemService;

	@ApiOperation("字典详细分页查询")
	@GetMapping("page")
	@PreAuthorize("hasAuthority('sys:dict:list')")
	public Result<PageData<SysDictItemDTO>> page(PageQuery pageQuery, SysDictItemQuery params) {
		String order = pageQuery.getOrder();
		pageQuery.setOrder(StringUtil.isNotBlank(order) ? order + "," + "sort#asc" : "sort#asc");
		PageData<SysDictItemDTO> page = sysDictItemService.queryPage(pageQuery, params);
		return Result.success(page);
	}

	@ApiOperation("字典详细")
	@GetMapping("{id}")
	@PreAuthorize("hasAuthority('sys:dict:info')")
	public Result<SysDictItemDTO> get(@PathVariable("id") Long id) {
		SysDictItemDTO sysDictItemDTO = sysDictItemService.queryById(id);
		return Result.success(sysDictItemDTO);
	}

	@ApiOperation("字典详情批量查询")
	@GetMapping("/map")
	@PreAuthorize("hasAuthority('sys:dict:info')")
	public Result<Map<String, List<SysDictItemDTO>>> map(@RequestParam("types") List<String> types) {
		List<SysDictItem> itemList = sysDictItemService
				.list(Wrappers.<SysDictItem>lambdaQuery().in(SysDictItem::getDictType, types));
		List<SysDictItemDTO> list = BeanUtil.copyList(itemList, SysDictItemDTO.class);
		Map<String, List<SysDictItemDTO>> map = list.stream()
				.collect(Collectors.groupingBy(SysDictItemDTO::getDictType));
		return Result.success(map);
	}

	@RequestLog("保存字典详细")
	@ApiOperation("保存字典详细")
	@PostMapping
	@PreAuthorize("hasAuthority('sys:dict:save')")
	public Result<Void> save(@RequestBody SysDictItemDTO dto) {
		sysDictItemService.saveOne(dto);
		return Result.success();
	}

	@RequestLog("更新字典详情")
	@ApiOperation("更新字典详情")
	@PutMapping
	@PreAuthorize("hasAuthority('sys:dict:update')")
	public Result<Void> update(@RequestBody SysDictItemDTO dto) {
		sysDictItemService.modifyById(dto);
		return Result.success();
	}

	@RequestLog("删除字典详情")
	@ApiOperation("删除字典详情")
	@DeleteMapping
	@PreAuthorize("hasAuthority('sys:dict:delete')")
	public Result<Void> delete(@RequestBody List<Long> ids) {
		sysDictItemService.removeBatchByIds(ids);
		return Result.success();
	}
}
