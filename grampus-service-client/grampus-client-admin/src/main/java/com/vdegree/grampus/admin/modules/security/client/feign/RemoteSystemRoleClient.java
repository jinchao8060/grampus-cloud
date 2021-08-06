package com.vdegree.grampus.admin.modules.security.client.feign;

import com.vdegree.grampus.common.core.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * RemoteSystemRoleClient
 *
 * @author Beck
 * @since 2021-08-05
 */
@FeignClient(contextId="remoteSystemRoleClient", name = "grampus-service-admin", path = "/rmi/system/role")
public interface RemoteSystemRoleClient {

	@GetMapping("/getRoleIds")
	Result<List<Long>> getRoleIds(@RequestParam("userId") Long userId);

	@GetMapping("/getRoleIds")
	Result<String> getPermissionsByRoleIds(@RequestParam("roleIds") List<Long> roleIds);

	@GetMapping("/getRoleIds")
	Result<String> getPermissions(@RequestParam("userId") Long userId);

	@GetMapping("/getRoleIds")
	Result<String> getAllPermissions();
}
