package com.oceancloud.grampus.admin.modules.testtx.controller;

import com.oceancloud.grampus.auth.modules.system.client.feign.RemoteAuthTestClient;
import com.oceancloud.grampus.framework.core.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
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
@RestController
@RequestMapping("/admin/test")
public class AdminTestController {

	@Autowired
	private RemoteAuthTestClient remoteAuthTestClient;

	@Value("${spring.cloud.nacos.discovery.metadata.gray.version}")
	private String version;

	@PostMapping("/demo")
	public Result<String> demo() {
		String text = remoteAuthTestClient.demo().getData();
		log.debug("text:{}", text);
		return Result.success(text);
	}

	@GetMapping("/version")
	public Result<String> version() {
		return Result.success(version);
	}
}
