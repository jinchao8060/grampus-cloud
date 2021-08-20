package com.oceancloud.grampus.admin.modules.system.controller;

import com.oceancloud.grampus.admin.code.ErrorCode;
import com.oceancloud.grampus.admin.modules.system.dto.SysMenuDTO;
import com.oceancloud.grampus.framework.oauth2.modules.system.utils.SystemSecurityUtils;
import com.oceancloud.grampus.admin.modules.system.service.SysLanguageService;
import com.oceancloud.grampus.admin.modules.system.service.SysMenuService;
import com.oceancloud.grampus.framework.core.utils.tree.TreeUtils;
import com.oceancloud.grampus.framework.core.result.Result;
import com.oceancloud.grampus.framework.log.annotation.RequestLog;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * 菜单管理
 *
 * @author Beck
 * @since 2021-01-21
 */
@Api(tags = "菜单模块")
@RestController
@AllArgsConstructor
@RequestMapping("/system/menu")
public class SysMenuController {

	private final SysMenuService sysMenuService;
	private final SysLanguageService sysLanguageService;

	@ApiOperation("导航栏")
	@GetMapping("nav")
	public Result<List<SysMenuDTO>> nav() {
		List<SysMenuDTO> list = sysMenuService.getUserMenuNavList(SystemSecurityUtils.getUserDetails());
		return Result.success(list);
	}

	@ApiOperation("菜单权限")
	@GetMapping("permissions")
	public Result<Set<String>> permissions() {
		Set<String> permissions = sysMenuService.getUserPermissions(SystemSecurityUtils.getUserDetails());
		return Result.success(permissions);
	}

	@ApiOperation("菜单列表")
	@ApiImplicitParam(name = "type", value = "菜单类型 0：菜单 1：按钮 null：全部", paramType = "query", dataType = "int")
	@GetMapping("list")
	@PreAuthorize("hasAuthority('sys:menu:list')")
	public Result<List<SysMenuDTO>> list(Integer type) {
		List<SysMenuDTO> list = sysMenuService.getMenuList(type);
		return Result.success(TreeUtils.build(list));
	}

	@ApiOperation("菜单信息")
	@GetMapping("{id}")
	@PreAuthorize("hasAuthority('sys:menu:info')")
	public Result<SysMenuDTO> get(@PathVariable("id") Long id) {
		SysMenuDTO data = sysMenuService.queryById(id);
//		if (ObjectUtil.isNotNull(data) && ObjectUtil.isNotNull(data.getId())) {
//			String menuName = sysLanguageService.convertFieldValue("sys_menu", data.getId(), "menu_name", WebUtil.getAcceptLanguage());
//			data.setMenuName(menuName);
//		}
		return Result.success(data);
	}

	@RequestLog("保存菜单")
	@ApiOperation("保存菜单")
	@PostMapping
	@PreAuthorize("hasAuthority('sys:menu:save')")
	public Result<Void> save(@RequestBody SysMenuDTO dto) {
		sysMenuService.save(dto);
		return Result.success();
	}

	@RequestLog("更新菜单")
	@ApiOperation("更新菜单")
	@PutMapping
	@PreAuthorize("hasAuthority('sys:menu:update')")
	public Result<Void> update(@RequestBody SysMenuDTO dto) {
		sysMenuService.modifyById(dto);
		return Result.success();
	}

	@RequestLog("删除菜单")
	@ApiOperation("删除菜单")
	@DeleteMapping("{id}")
	@PreAuthorize("hasAuthority('sys:menu:delete')")
	public Result<Void> delete(@PathVariable("id") Long id) {
		//判断是否有子菜单或按钮
		List<SysMenuDTO> list = sysMenuService.getListByPid(id);
		if (list.size() > 0) {
			return Result.error(ErrorCode.System.SUB_MENU_EXIST.getCode());
		}
		sysMenuService.deleteById(id);
		return Result.success();
	}
}