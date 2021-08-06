package com.vdegree.grampus.admin.modules.system.controller;

import com.vdegree.grampus.admin.modules.system.dto.SysDictItemDTO;
import com.vdegree.grampus.admin.modules.system.service.SysDictItemService;
import com.vdegree.grampus.common.core.constant.Constant;
import com.vdegree.grampus.common.core.result.Result;
import com.vdegree.grampus.common.core.utils.StringUtil;
import com.vdegree.grampus.common.log.annotation.RequestLog;
import com.vdegree.grampus.common.mybatis.page.PageData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * 字典数据项接口
 *
 * @author Beck
 * @since 2021-04-09
 */
@Api(tags = "字典详情管理")
@RestController
@AllArgsConstructor
@RequestMapping("/system/dict/item")
public class SysDictItemController {

	private final SysDictItemService sysDictItemService;

	@ApiOperation("字典详细分页查询")
	@GetMapping("page")
	@ApiImplicitParams({
			@ApiImplicitParam(name = Constant.PAGE_NUM, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
			@ApiImplicitParam(name = Constant.PAGE_SIZE, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
			@ApiImplicitParam(name = Constant.ORDER, value = "排序条件(field1#asc,field2#desc)", paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = Constant.WITH_COUNT, value = "查询数据总量(true、false)", paramType = "query", dataType = "Boolean"),
			@ApiImplicitParam(name = "dictId", value = "字典ID", paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "dictType", value = "字典类型(字典ID与类型二选一即可)", paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "dictLabel", value = "字典标签", paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "dictValue", value = "字典值", paramType = "query", dataType = "String")
	})
	@PreAuthorize("hasAuthority('sys:dict:list')")
	public Result<PageData<SysDictItemDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
		String order = (String) params.get(Constant.ORDER);
		params.put(Constant.ORDER, StringUtil.isNotBlank(order) ? order + "," + "sort#asc" : "sort#asc");
		PageData<SysDictItemDTO> page = sysDictItemService.queryPage(params);
		return Result.success(page);
	}

	@ApiOperation("字典详细")
	@GetMapping("{id}")
	@PreAuthorize("hasAuthority('sys:dict:info')")
	public Result<SysDictItemDTO> get(@PathVariable("id") Long id) {
		SysDictItemDTO sysDictItemDTO = sysDictItemService.queryById(id);
		return Result.success(sysDictItemDTO);
	}

	@RequestLog("保存字典详细")
	@ApiOperation("保存字典详细")
	@PostMapping
	@PreAuthorize("hasAuthority('sys:dict:save')")
	public Result<Void> save(@RequestBody SysDictItemDTO dto) {
		sysDictItemService.save(dto);
		return Result.success();
	}

	@RequestLog("更新字典详情")
	@ApiOperation("更新字典详情")
	@PutMapping
	@PreAuthorize("hasAuthority('sys:dict:update')")
	public Result<Void> update(@RequestBody SysDictItemDTO dto) {
		sysDictItemService.modifyById(dto);
		return Result.success();
	}

	@RequestLog("删除字典详情")
	@ApiOperation("删除字典详情")
	@DeleteMapping
	@PreAuthorize("hasAuthority('sys:dict:delete')")
	public Result<Void> delete(@RequestBody List<Long> ids) {
		sysDictItemService.deleteBatchIds(ids);
		return Result.success();
	}
}
