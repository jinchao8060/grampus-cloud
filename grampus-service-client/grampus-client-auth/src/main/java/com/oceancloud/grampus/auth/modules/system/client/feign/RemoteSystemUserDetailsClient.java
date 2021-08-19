package com.oceancloud.grampus.auth.modules.system.client.feign;

import com.oceancloud.grampus.framework.core.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * RemoteSystemUserDetailsClient
 *
 * @author Beck
 * @since 2021-08-12
 */
@FeignClient(contextId="remoteSystemUserDetailsClient", name = "grampus-service-auth", path = "/auth/system/userDetails")
public interface RemoteSystemUserDetailsClient {

	@PostMapping("/removeSystemUserDetails")
	Result<Void> removeSystemUserDetails(@RequestBody String userNo);
}
