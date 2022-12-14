package com.oceancloud.grampus.admin.modules.security.client.feign;

import com.oceancloud.grampus.framework.core.result.Result;
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

	@GetMapping("/getPermissionsByRoleIds")
	Result<String> getPermissionsByRoleIds(@RequestParam("roleIds") List<Long> roleIds);

	@GetMapping("/getPermissions")
	Result<String> getPermissions(@RequestParam("userId") Long userId);

	@GetMapping("/getAllPermissions")
	Result<String> getAllPermissions();
}
