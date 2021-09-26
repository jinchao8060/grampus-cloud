package com.oceancloud.grampus.admin.modules.system.controller;

import com.oceancloud.grampus.admin.modules.system.dto.SysDeptDTO;
import com.oceancloud.grampus.admin.modules.system.service.SysDeptService;
import com.oceancloud.grampus.framework.core.result.Result;
import com.oceancloud.grampus.framework.core.utils.tree.TreeUtil;
import com.oceancloud.grampus.framework.log.annotation.RequestLog;
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
 * 部门管理
 *
 * @author Beck
 * @since 2021-01-21
 */
@Api(tags = "部门管理")
@RestController
@AllArgsConstructor
@RequestMapping("/system/dept")
public class SysDeptController {

	private final SysDeptService sysDeptService;

	@ApiOperation("部门列表")
	@GetMapping("/list")
	@PreAuthorize("hasAuthority('sys:dept:list')")
	public Result<List<SysDeptDTO>> list() {
		List<SysDeptDTO> result = sysDeptService.queryAll();
		return Result.success(TreeUtil.build(result));
	}

	@ApiOperation("部门信息")
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('sys:dept:info')")
	public Result<SysDeptDTO> get(@PathVariable Long id) {
		SysDeptDTO result = sysDeptService.queryById(id);
		return Result.success(result);
	}

	@RequestLog("保存部门")
	@ApiOperation("保存部门")
	@PostMapping()
	@PreAuthorize("hasAuthority('sys:dept:save')")
	public Result<Void> save(@RequestBody SysDeptDTO sysDeptDTO) {
		sysDeptService.save(sysDeptDTO);
		return Result.success();
	}

	@RequestLog("更新部门")
	@ApiOperation("更新部门")
	@PutMapping()
	@PreAuthorize("hasAuthority('sys:dept:update')")
	public Result<Void> update(@RequestBody SysDeptDTO sysDeptDTO) {
		sysDeptService.modifyById(sysDeptDTO);
		return Result.success();
	}

	@RequestLog("删除部门")
	@ApiOperation("删除部门")
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('sys:dept:delete')")
	public Result<Void> delete(@PathVariable Long id) {
		sysDeptService.deleteById(id);
		return Result.success();
	}
}
