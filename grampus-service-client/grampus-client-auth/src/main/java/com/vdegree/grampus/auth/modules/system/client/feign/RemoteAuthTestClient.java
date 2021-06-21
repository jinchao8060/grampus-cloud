package com.vdegree.grampus.auth.modules.system.client.feign;

import com.vdegree.grampus.common.core.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * RemoteAuthTestClient
 *
 * @author Beck
 * @since 2021-06-17
 */
@FeignClient(contextId="remoteAuthTestClient", name = "grampus-service-auth")
public interface RemoteAuthTestClient {

	@PostMapping("/auth/test/demo")
	Result<String> demo();
}
