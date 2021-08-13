package com.vdegree.grampus.auth.modules.system.client.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * RemoteOAuthTokenClient
 *
 * @author Beck
 * @since 2021-08-12
 */
@FeignClient(contextId = "remoteOAuthTokenClient", name = "grampus-service-auth", path = "/oauth")
public interface RemoteOAuthTokenClient {

	@PostMapping("/token")
	Map<String, Object> oauthToken(@RequestParam("username") String username,
								   @RequestParam("password") String password,
								   @RequestParam("grant_type") String grant_type,
								   @RequestParam("scope") String scope,
								   @RequestParam("client_id") String client_id,
								   @RequestParam("client_secret") String client_secret);
}
