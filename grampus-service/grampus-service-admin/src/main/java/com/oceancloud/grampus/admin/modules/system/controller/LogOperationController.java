package com.oceancloud.grampus.admin.modules.system.controller;

import com.oceancloud.grampus.admin.modules.system.query.LogOperationQuery;
import com.oceancloud.grampus.framework.core.result.Result;
import com.oceancloud.grampus.framework.mybatis.page.PageData;
import com.oceancloud.grampus.framework.mybatis.page.PageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.oceancloud.grampus.admin.modules.system.dto.LogOperationDTO;
import com.oceancloud.grampus.admin.modules.system.service.LogOperationService;

/**
 * 操作日志表(LogOperation) 表控制层
 *
 * @author Beck
 * @since 2021-05-31
 */
@Api(tags = "日志管理")
@RestController
@AllArgsConstructor
@RequestMapping("/system/log/operation")
public class LogOperationController {

	private final LogOperationService logOperationService;

	@ApiOperation("操作日志分页查询")
	@GetMapping("page")
	@PreAuthorize("hasAuthority('log:operation:list')")
	public Result<PageData<LogOperationDTO>> page(PageQuery pageQuery, LogOperationQuery params) {
		PageData<LogOperationDTO> page = logOperationService.queryPage(pageQuery, params);
		return Result.success(page);
	}
}
