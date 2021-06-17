package com.vdegree.grampus.admin.modules.system.controller;

import com.vdegree.grampus.common.core.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AdminTestController
 *
 * @author Beck
 * @since 2021-06-16
 */
@RestController
@RequestMapping("/admin/test")
public class AdminTestController {

	@PostMapping("/demo")
	public Result<String> demo() {
		String text = "admin test demo";
		return Result.success(text);
	}
}
