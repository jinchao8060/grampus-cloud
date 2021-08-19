package com.oceancloud.grampus.auth.modules.testtx.controller;

import com.oceancloud.grampus.admin.modules.security.client.dto.SystemUserDTO;
import com.oceancloud.grampus.admin.modules.security.client.feign.RemoteSystemUserClient;
import com.oceancloud.grampus.admin.modules.system.client.dto.TestTxDTO;
import com.oceancloud.grampus.admin.modules.system.client.feign.RemoteTestTxClient;
import com.oceancloud.grampus.auth.modules.testtx.service.TestTxService;
import com.oceancloud.grampus.framework.core.result.Result;
import com.oceancloud.grampus.framework.mybatis.page.PageData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * TestController
 *
 * @author Beck
 * @since 2021-06-16
 */
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/auth/test")
public class AuthTestController {

	private final RemoteTestTxClient remoteTestTxClient;
	private final TestTxService testTxService;
	private final RemoteSystemUserClient remoteSystemUserClient;

	@PostMapping("/demo")
	public Result<Object> demo(@RequestParam String userNo) {
		String text = "auth test demo";
		log.debug("text:{}", text);
		SystemUserDTO user = remoteSystemUserClient.getSysUserByUserNo(userNo).getData();
		return Result.success(user);
	}

	@PostMapping("/save")
	public Result<Void> txSave(@RequestBody TestTxDTO testTxDTO) {
		remoteTestTxClient.save(testTxDTO);
		return Result.success();
	}

	@PostMapping("/update")
	public Result<Void> txUpdate(@RequestBody TestTxDTO testTxDTO) {
		remoteTestTxClient.update(testTxDTO);
		return Result.success();
	}

	@PostMapping("/test")
	public Result<Void> test(@RequestBody TestTxDTO testTxDTO) {
		testTxService.update(testTxDTO);
		return Result.success();
	}

	@GetMapping("/page")
	public Result<PageData<TestTxDTO>> txPage(@RequestParam Map<String, Object> params) {
		return remoteTestTxClient.page(params);
	}
}
