package com.vdegree.grampus.auth.modules.system.controller;

import com.vdegree.grampus.admin.modules.system.client.dto.TestTxDTO;
import com.vdegree.grampus.admin.modules.system.client.feign.RemoteTestTxClient;
import com.vdegree.grampus.auth.modules.system.service.TestTxService;
import com.vdegree.grampus.common.core.result.Result;
import com.vdegree.grampus.common.mybatis.page.PageData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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

	@PostMapping("/demo")
	public Result<Object> demo() {
		String text = "auth test demo";
		log.debug("text:{}", text);
		return Result.success(text);
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
