package com.oceancloud.grampus.admin.modules.security.controller;

import com.oceancloud.grampus.admin.modules.security.roles.SystemRoleService;
import com.oceancloud.grampus.framework.core.result.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * RemoteSystemRoleController
 *
 * @author Beck
 * @since 2021-08-05
 */
@RestController
@AllArgsConstructor
@RequestMapping("/rmi/system/role")
public class RemoteSystemRoleController {

	private final SystemRoleService systemRoleService;

	@GetMapping("/getRoleIds")
	public Result<List<Long>> getRoleIds(@RequestParam("userId") Long userId) {
		List<Long> result = systemRoleService.getRoleIds(userId);
		return Result.success(result);
	}

	@GetMapping("/getPermissionsByRoleIds")
	public Result<String> getPermissionsByRoleIds(@RequestParam("roleIds") List<Long> roleIds) {
		String result = systemRoleService.getPermissionsByRoleIds(roleIds);
		return Result.success(result);
	}

	@GetMapping("/getPermissions")
	public Result<String> getPermissions(@RequestParam("userId") Long userId) {
		String result = systemRoleService.getPermissions(userId);
		return Result.success(result);
	}

	@GetMapping("/getAllPermissions")
	public Result<String> getAllPermissions() {
		String result = systemRoleService.getAllPermissions();
		return Result.success(result);
	}
}
