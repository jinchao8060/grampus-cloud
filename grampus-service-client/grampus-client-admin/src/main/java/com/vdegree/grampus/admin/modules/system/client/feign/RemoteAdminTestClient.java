package com.vdegree.grampus.admin.modules.system.client.feign;

/**
 * RemoteAdminTestClient
 *
 * @author Beck
 * @since 2021-06-17
 */
@FeignClient(contextId="remoteAdminTestClient", value = "grampus-service-admin")
public class RemoteAdminTestClient {

	@PostMapping("/admin/test/demo")
	public Result<String> demo();
}
