package com.oceancloud.grampus.admin.modules.testtx.controller;

import com.oceancloud.grampus.auth.modules.system.client.feign.RemoteAuthTestClient;
import com.oceancloud.grampus.framework.core.result.Result;
//import com.oceancloud.grampus.framework.gray.support.GrayVersionContextHolder;
//import com.oceancloud.grampus.framework.rabbitmq.service.MessageQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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

//	@Autowired
//	private MessageQueue messageQueue;
//
//	@PostMapping("/rabbit/{version}")
//	@ApiOperation("测试Rabbit")
//	public Result<Void> test2(@PathVariable("version") String version) {
//		GrayVersionContextHolder.setVersion(version);
//
//		ArrayList<TestDTO> objects = Lists.newArrayList();
//		objects.add(new TestDTO(1L, "1"));
//		objects.add(new TestDTO(2L, "2"));
//
//		messageQueue.sendMessage("queue.direct.1", objects);
//		GrayVersionContextHolder.clear();
//		return Result.success();
//	}
}
