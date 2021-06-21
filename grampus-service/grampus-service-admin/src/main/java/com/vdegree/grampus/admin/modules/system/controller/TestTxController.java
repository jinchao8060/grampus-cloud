package com.vdegree.grampus.admin.modules.system.controller;

import com.vdegree.grampus.common.core.result.Result;
import com.vdegree.grampus.common.mybatis.page.PageData;
import io.seata.spring.annotation.GlobalTransactional;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vdegree.grampus.admin.modules.system.client.dto.TestTxDTO;
import com.vdegree.grampus.admin.modules.system.service.TestTxService;

import java.util.Map;

/**
 * (TestTx) 表控制层
 *
 * @author Beck
 * @since 2021-06-21 17:13:18
 */
@Api(tags = "")
@RestController
@AllArgsConstructor
@RequestMapping("testTx")
public class TestTxController {

	private final TestTxService testTxService;

	@GetMapping("page")
	public Result<PageData<TestTxDTO>> page(@RequestParam Map<String, Object> params) {
		PageData<TestTxDTO> page = testTxService.queryPage(params);
		return Result.success(page);
	}

	@GetMapping("{id}")
	public Result<TestTxDTO> get(@PathVariable("id") Long id) {
		TestTxDTO result = testTxService.queryById(id);
		return Result.success(result);
	}

	@PostMapping
	@GlobalTransactional
	public Result<Void> save(@RequestBody TestTxDTO params) {
		testTxService.save(params);
		return Result.success();
	}

	@PutMapping
	@GlobalTransactional
	public Result<Void> update(@RequestBody TestTxDTO params) {
		testTxService.modifyById(params);
		return Result.success();
	}

	@DeleteMapping("/{id}")
	@GlobalTransactional
	public Result<Void> delete(@PathVariable Long id) {
		testTxService.deleteById(id);
		return Result.success();
	}
}
