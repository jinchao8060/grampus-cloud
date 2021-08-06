package com.vdegree.grampus.admin.modules.system.controller;

import com.vdegree.grampus.admin.modules.system.dto.SysDictDTO;
import com.vdegree.grampus.admin.modules.system.service.SysDictService;
import com.vdegree.grampus.common.core.constant.Constant;
import com.vdegree.grampus.common.core.result.Result;
import com.vdegree.grampus.common.core.utils.StringUtil;
import com.vdegree.grampus.common.log.annotation.RequestLog;
import com.vdegree.grampus.common.mybatis.page.PageData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

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
	@ApiImplicitParams({
			@ApiImplicitParam(name = Constant.PAGE_NUM, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
			@ApiImplicitParam(name = Constant.PAGE_SIZE, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
			@ApiImplicitParam(name = Constant.ORDER, value = "排序条件(field1#asc,field2#desc)", paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = Constant.WITH_COUNT, value = "查询数据总量(true、false)", paramType = "query", dataType = "Boolean"),
			@ApiImplicitParam(name = "dictType", value = "字典类型", paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "dictName", value = "字典名称", paramType = "query", dataType = "String")
	})
	@GetMapping("page")
	@PreAuthorize("hasAuthority('sys:dict:list')")
	public Result<PageData<SysDictDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
		String order = (String) params.get(Constant.ORDER);
		params.put(Constant.ORDER, StringUtil.isNotBlank(order) ? order + "," + "sort#asc" : "sort#asc");
		PageData<SysDictDTO> page = sysDictService.queryPage(params);
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
