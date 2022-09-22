package com.oceancloud.grampus.admin.modules.system.controller;

import com.oceancloud.grampus.admin.modules.system.service.SysLanguageService;
import com.oceancloud.grampus.framework.core.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 系统测试管理
 *
 * @author Beck
 * @since 2021-06-08
 */
@Api(tags = "测试管理")
@AllArgsConstructor
@RestController
@RequestMapping("/system/test")
public class SysTestController {

	private final SysLanguageService sysLanguageService;

	@PostMapping("/lang")
	@ApiOperation("测试多语言")
	@PreAuthorize("hasAuthority('sys:user:info')")
	public Result<Map<Long,String>> get(@RequestBody List<Long> tableIds) {
		Map<Long,String> result = sysLanguageService.convertFieldValue("sys_menu", tableIds, "menu_name", "en-US");
		return Result.success(result);
	}

	@PostMapping("/lang1")
	@ApiOperation("测试多语言")
	@PreAuthorize("hasAuthority('sys:user:info')")
	public Result<String> get(@RequestBody Long tableId) {
		String result = sysLanguageService.convertFieldValue("sys_menu", tableId, "menu_name", "en-US");
		return Result.success(result);
	}

	@PostMapping("/location")
	@ApiOperation("测试重定向")
	@PreAuthorize("hasAuthority('sys:user:info')")
	public void test(HttpServletResponse response) throws IOException {
		response.sendRedirect("https://host-test.zetarapp.cn/zt/#/login");
//		response.sendRedirect("https://www.baidu.com");
	}
}
