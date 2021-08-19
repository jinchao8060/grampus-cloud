package com.oceancloud.grampus.admin.modules.system.controller;

import com.oceancloud.grampus.admin.modules.system.dto.SysRoleDTO;
import com.oceancloud.grampus.admin.modules.system.service.SysRoleMenuService;
import com.oceancloud.grampus.admin.modules.system.service.SysRoleService;
import com.oceancloud.grampus.framework.core.constant.Constant;
import com.oceancloud.grampus.framework.core.result.Result;
import com.oceancloud.grampus.framework.log.annotation.RequestLog;
import com.oceancloud.grampus.framework.mybatis.page.PageData;
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
 * 系统角色管理
 *
 * @author Beck
 * @since 2021-01-21
 */
@Api(tags = "系统角色管理")
@RestController
@AllArgsConstructor
@RequestMapping("/system/role")
public class SysRoleController {

	private final SysRoleService sysRoleService;
	private final SysRoleMenuService sysRoleMenuService;

	@ApiOperation("角色分页查询")
	@GetMapping("page")
	@ApiImplicitParams({
			@ApiImplicitParam(name = Constant.PAGE_NUM, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
			@ApiImplicitParam(name = Constant.PAGE_SIZE, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
			@ApiImplicitParam(name = Constant.ORDER, value = "排序条件(field1#asc,field2#desc)", paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = Constant.WITH_COUNT, value = "查询数据总量(true、false)", paramType = "query", dataType = "Boolean"),
			@ApiImplicitParam(name = "roleCode", value = "角色编号", paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "roleName", value = "角色名", paramType = "query", dataType = "String")
	})
	@PreAuthorize("hasAuthority('sys:role:list')")
	public Result<PageData<SysRoleDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
		PageData<SysRoleDTO> result = sysRoleService.queryPage(params);
		return Result.success(result);
	}

	@ApiOperation("角色列表")
	@GetMapping("list")
	@PreAuthorize("hasAuthority('sys:role:list')")
	public Result<List<SysRoleDTO>> list() {
		List<SysRoleDTO> result = sysRoleService.queryAll();
		return Result.success(result);
	}

	@ApiOperation("角色信息")
	@GetMapping("{id}")
	@PreAuthorize("hasAuthority('sys:role:info')")
	public Result<SysRoleDTO> get(@PathVariable("id") Long id) {
		SysRoleDTO result = sysRoleService.queryById(id);
		List<Long> menuIdList = sysRoleMenuService.getMenuIdList(id);
		result.setMenuIdList(menuIdList);
		return Result.success(result);
	}

	@RequestLog("保存角色")
	@ApiOperation("保存角色")
	@PostMapping
	@PreAuthorize("hasAuthority('sys:role:save')")
	public Result<Void> save(@RequestBody SysRoleDTO dto) {
		sysRoleService.save(dto);
		return Result.success();
	}

	@RequestLog("修改角色")
	@ApiOperation("修改角色")
	@PutMapping
	@PreAuthorize("hasAuthority('sys:role:update')")
	public Result<Void> update(@RequestBody SysRoleDTO dto) {
		sysRoleService.modifyById(dto);
		return Result.success();
	}

	@RequestLog("删除角色")
	@ApiOperation("删除角色")
	@DeleteMapping
	@PreAuthorize("hasAuthority('sys:role:delete')")
	public Result<Void> delete(@RequestBody List<Long> ids) {
		sysRoleService.deleteBatchIds(ids);
		return Result.success();
	}
}