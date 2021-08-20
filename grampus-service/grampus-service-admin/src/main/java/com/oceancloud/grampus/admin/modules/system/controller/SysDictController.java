package com.oceancloud.grampus.admin.modules.system.controller;

import com.oceancloud.grampus.admin.modules.system.dto.SysDictDTO;
import com.oceancloud.grampus.admin.modules.system.query.SysDictQuery;
import com.oceancloud.grampus.admin.modules.system.service.SysDictService;
import com.oceancloud.grampus.framework.core.result.Result;
import com.oceancloud.grampus.framework.core.utils.StringUtil;
import com.oceancloud.grampus.framework.log.annotation.RequestLog;
import com.oceancloud.grampus.framework.mybatis.page.PageData;
import com.oceancloud.grampus.framework.mybatis.page.PageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 字典管理
 *
 * @author Beck
 * @since 2021-01-21
 */
@Api(tags = "字典管理")
@RestController
@AllArgsConstructor
@RequestMapping("/system/dict")
public class SysDictController {

	private final SysDictService sysDictService;

	@ApiOperation("字典分页查询")
	@GetMapping("page")
	@PreAuthorize("hasAuthority('sys:dict:list')")
	public Result<PageData<SysDictDTO>> page(PageQuery pageQuery, SysDictQuery params) {
		String order = pageQuery.getOrder();
		pageQuery.setOrder(StringUtil.isNotBlank(order) ? order + "," + "sort#asc" : "sort#asc");
		PageData<SysDictDTO> page = sysDictService.queryPage(pageQuery, params);
		return Result.success(page);
	}

	@ApiOperation("字典信息")
	@GetMapping("{id}")
	@PreAuthorize("hasAuthority('sys:dict:info')")
	public Result<SysDictDTO> get(@PathVariable("id") Long id) {
		SysDictDTO result = sysDictService.queryById(id);
		return Result.success(result);
	}

	@RequestLog("保存字典")
	@ApiOperation("保存字典")
	@PostMapping
	@PreAuthorize("hasAuthority('sys:dict:save')")
	public Result<Void> save(@RequestBody SysDictDTO params) {
		sysDictService.save(params);
		return Result.success();
	}

	@RequestLog("更新字典")
	@ApiOperation("更新字典")
	@PutMapping
	@PreAuthorize("hasAuthority('sys:dict:update')")
	public Result<Void> update(@RequestBody SysDictDTO params) {
		sysDictService.modifyById(params);
		return Result.success();
	}

	@RequestLog("删除字典")
	@ApiOperation("删除字典")
	@DeleteMapping
	@PreAuthorize("hasAuthority('sys:dict:delete')")
	public Result<Void> delete(@RequestBody List<Long> ids) {
		sysDictService.deleteBatchIds(ids);
		return Result.success();
	}
}
