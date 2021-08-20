package com.oceancloud.grampus.admin.modules.system.controller;

import com.oceancloud.grampus.admin.modules.system.dto.SysParamDTO;
import com.oceancloud.grampus.admin.modules.system.excel.SysParamExcel;
import com.oceancloud.grampus.admin.modules.system.query.SysParamQuery;
import com.oceancloud.grampus.admin.modules.system.service.SysParamService;
import com.oceancloud.grampus.framework.core.result.Result;
import com.oceancloud.grampus.framework.core.utils.BeanUtil;
import com.oceancloud.grampus.framework.excel.annotation.ResponseExcel;
import com.oceancloud.grampus.framework.log.annotation.RequestLog;
import com.oceancloud.grampus.framework.mybatis.page.PageData;
import com.oceancloud.grampus.framework.mybatis.page.PageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
 * 系统参数管理
 *
 * @author Beck
 * @since 2021-01-21
 */
@Api(tags = "系统参数管理")
@RestController
@AllArgsConstructor
@RequestMapping("/system/param")
public class SysParamController {

	private final SysParamService sysParamService;

	@ApiOperation("参数分页查询")
	@GetMapping("page")
	@PreAuthorize("hasAuthority('sys:param:list')")
	public Result<PageData<SysParamDTO>> page(PageQuery pageQuery, SysParamQuery params) {
		PageData<SysParamDTO> result = sysParamService.queryPage(pageQuery, params);
		return Result.success(result);
	}

	@ApiOperation("参数信息")
	@GetMapping("{id}")
	@PreAuthorize("hasAuthority('sys:param:info')")
	public Result<SysParamDTO> get(@PathVariable("id") Long id) {
		SysParamDTO result = sysParamService.queryById(id);
		return Result.success(result);
	}

	@RequestLog("保存参数")
	@ApiOperation("保存参数")
	@PostMapping
	@PreAuthorize("hasAuthority('sys:param:save')")
	public Result<Void> save(@RequestBody SysParamDTO dto) {
		sysParamService.save(dto);
		return Result.success();
	}

	@RequestLog("修改参数")
	@ApiOperation("修改参数")
	@PutMapping
	@PreAuthorize("hasAuthority('sys:param:update')")
	public Result<Void> update(@RequestBody SysParamDTO dto) {
		sysParamService.modifyById(dto);
		return Result.success();
	}

	@RequestLog("删除参数")
	@ApiOperation("删除参数")
	@DeleteMapping
	@PreAuthorize("hasAuthority('sys:param:delete')")
	public Result<Void> delete(@RequestBody List<Long> ids) {
		sysParamService.deleteBatchIds(ids);
		return Result.success();
	}

	@RequestLog("导出系统参数Excel")
	@ApiOperation("Excel导出")
	@GetMapping("export")
	@ResponseExcel(name = "系统参数")
	@PreAuthorize("hasAuthority('sys:param:export')")
	@ApiImplicitParam(name = "code", value = "参数编码", paramType = "query", dataType = "String")
	public List<SysParamExcel> export(@ApiIgnore @RequestParam Map<String, Object> params) {
		List<SysParamDTO> list = sysParamService.queryList(params);
		return BeanUtil.copyList(list, SysParamExcel.class);
	}
}