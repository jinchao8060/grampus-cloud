package com.oceancloud.grampus.admin.modules.security.client.feign;

import com.oceancloud.grampus.admin.modules.security.client.dto.SystemUserDTO;
import com.oceancloud.grampus.framework.core.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * RemoteSystemUserClient
 *
 * @author Beck
 * @since 2021-08-05
 */
@FeignClient(contextId="remoteSystemUserClient", name = "grampus-service-admin", path = "/rmi/system/user")
public interface RemoteSystemUserClient {

	@GetMapping("/getSysUserByUserNo")
	Result<SystemUserDTO> getSysUserByUserNo(@RequestParam("userNo") String userNo);
}
