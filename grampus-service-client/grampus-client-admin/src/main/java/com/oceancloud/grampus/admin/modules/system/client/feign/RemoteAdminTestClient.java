package com.oceancloud.grampus.admin.modules.system.client.feign;

import com.oceancloud.grampus.framework.core.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * RemoteAdminTestClient
 *
 * @author Beck
 * @since 2021-06-17
 */
@FeignClient(contextId="remoteAdminTestClient", value = "grampus-service-admin")
public interface RemoteAdminTestClient {

	@PostMapping("/admin/test/demo")
	Result<String> demo();
}
