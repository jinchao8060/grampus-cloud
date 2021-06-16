package com.vdegree.grampus.auth.modules.system;

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
@RequestMapping("/test")
public class TestController {

	@RequestMapping("/demo")
	public Result<Object> demo() {
		String text = "text";
		return Result.success(text);
	}
}
