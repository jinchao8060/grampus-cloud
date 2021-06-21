package com.vdegree.grampus.admin.modules.system.controller;

import com.vdegree.grampus.auth.modules.system.client.feign.RemoteAuthTestClient;
import com.vdegree.grampus.common.core.result.Result;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AdminTestController
 *
 * @author Beck
 * @since 2021-06-16
 */
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/admin/test")
public class AdminTestController {
	
	private final RemoteAuthTestClient remoteAuthTestClient;

	@PostMapping("/demo")
	public Result<String> demo() {
		String text = remoteAuthTestClient.demo().getData();
		log.debug("text:{}", text);
		return Result.success(text);
	}
}
