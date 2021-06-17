package com.vdegree.grampus.auth.modules.system.client.feign;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * RemoteAuthTestClient
 *
 * @author Beck
 * @since 2021-06-17
 */
@FeignClient(contextId="remoteAuthTestClient", name = "grampus-service-auth")
public class RemoteAuthTestClient {

	@PostMapping("/auth/test/demo")
	public Result<Object> demo();
}
