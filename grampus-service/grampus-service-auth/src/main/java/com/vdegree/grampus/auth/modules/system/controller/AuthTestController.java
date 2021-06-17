package com.vdegree.grampus.auth.modules.system.controller;

import com.vdegree.grampus.common.core.result.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 *
 * @author Beck
 * @since 2021-06-16
 */
@RestController
@RequestMapping("/auth/test")
public class AuthTestController {

	@PostMapping("/demo")
	public Result<Object> demo() {
		String text = "auth test demo";
		return Result.success(text);
	}
}
