package com.oceancloud.grampus.admin.modules.system.controller;

import com.google.common.collect.Maps;
import com.oceancloud.grampus.admin.modules.security.pojo.LoginReq;
import com.oceancloud.grampus.auth.modules.system.client.feign.RemoteOAuthTokenClient;
import com.oceancloud.grampus.framework.oauth2.modules.system.users.SystemUserDetails;
import com.oceancloud.grampus.framework.oauth2.modules.system.utils.SystemSecurityUtils;
import com.oceancloud.grampus.framework.core.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 登陆控制器
 *
 * @author Beck
 * @since 2020-12-15
 */
@Api(tags = "登陆注册")
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/system")
public class LoginController {

	private final RemoteOAuthTokenClient remoteOAuthTokenClient;

	@ApiOperation("登录接口")
	@PostMapping("/login")
	public Object login(@RequestBody LoginReq params) {
		String username = params.getUserNo();
		String password = params.getPassword();

		Map<String, Object> result = remoteOAuthTokenClient.oauthToken(
				username, password, "password", "all",
				"client_system", "123456");

		if (result.get("status") != null) {
			return result;
		}

		result.put("token", "Bearer " + result.get("access_token"));

		return Result.success(result);
	}

	@ApiOperation("用户信息")
	@PostMapping("/userInfo")
	public Result userInfo() {
		Authentication t2 = SystemSecurityUtils.getAuthentication();

		SystemUserDetails userDetails = (SystemUserDetails) t2.getPrincipal();

		Map<String, Object> result = Maps.newHashMap();
		result.put("getAuthentication", t2);
		result.put("getUserDetails", userDetails);
		return Result.success(result);
	}
}
