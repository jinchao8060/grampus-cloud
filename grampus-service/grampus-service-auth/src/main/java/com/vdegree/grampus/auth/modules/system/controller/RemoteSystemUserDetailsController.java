package com.vdegree.grampus.auth.modules.system.controller;

import com.vdegree.grampus.auth.modules.security.redis.SystemUserDetailsRedis;
import com.vdegree.grampus.common.core.result.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RemoteSystemUserDetailsController
 *
 * @author Beck
 * @since 2021-08-12
 */
@RestController
@AllArgsConstructor
@RequestMapping("/auth/system/userDetails")
public class RemoteSystemUserDetailsController {

	private final SystemUserDetailsRedis systemUserDetailsRedis;

	@PostMapping("/removeSystemUserDetails")
	public Result<Void> removeSystemUserDetails(@RequestBody String userNo) {
		systemUserDetailsRedis.removeSystemUserDetails(userNo);
		return Result.success();
	}
}
