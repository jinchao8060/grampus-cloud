package com.oceancloud.grampus.admin.modules.system.controller;

import com.oceancloud.grampus.admin.code.ErrorCode;
import com.oceancloud.grampus.admin.modules.system.dto.PasswordDTO;
import com.oceancloud.grampus.admin.modules.system.dto.SysUserDTO;
import com.oceancloud.grampus.admin.modules.system.dto.SysUserReqDTO;
import com.oceancloud.grampus.framework.oauth2.modules.system.users.SystemUserDetails;
import com.oceancloud.grampus.framework.oauth2.modules.system.utils.SystemSecurityUtils;
import com.oceancloud.grampus.admin.modules.system.query.SysUserQuery;
import com.oceancloud.grampus.admin.modules.system.service.SysUserRoleService;
import com.oceancloud.grampus.admin.modules.system.service.SysUserService;
import com.oceancloud.grampus.framework.core.result.Result;
import com.oceancloud.grampus.framework.core.utils.BeanUtil;
import com.oceancloud.grampus.framework.log.annotation.RequestLog;
import com.oceancloud.grampus.framework.mybatis.page.PageData;
import com.oceancloud.grampus.framework.mybatis.page.PageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统用户管理
 *
 * @author Beck
 * @since 2021-01-21
 */
@Api(tags = "用户管理")
@AllArgsConstructor
@RestController
@RequestMapping("/system/user")
public class SysUserController {
	private final SysUserService sysUserService;
	private final SysUserRoleService sysUserRoleService;
	private final PasswordEncoder passwordEncoder;

	@GetMapping("page")
	@ApiOperation("分页")
	@PreAuthorize("hasAuthority('sys:user:list')")
	public Result<PageData<SysUserDTO>> page(PageQuery pageQuery, SysUserQuery params) {
		PageData<SysUserDTO> result = sysUserService.queryPage(pageQuery, params);
		return Result.success(result);
	}

	@GetMapping("{id}")
	@ApiOperation("信息")
	@PreAuthorize("hasAuthority('sys:user:info')")
	public Result<SysUserDTO> get(@PathVariable("id") Long id) {
		SysUserDTO result = sysUserService.queryById(id);
		// 用户角色列表
		List<Long> roleIdList = sysUserRoleService.getRoleIdList(id);
		result.setRoleIdList(roleIdList);
		return Result.success(result);
	}

	@ApiOperation("登录用户信息")
	@GetMapping("info")
	public Result<SysUserDTO> info() {
		SysUserDTO result = BeanUtil.copy(SystemSecurityUtils.getUserDetails(), SysUserDTO.class);
		return Result.success(result);
	}

	@RequestLog("保存用户")
	@ApiOperation("保存用户")
	@PostMapping
	@PreAuthorize("hasAuthority('sys:user:save')")
	public Result<Void> save(@RequestBody SysUserReqDTO reqDTO) {
		SysUserDTO dto = BeanUtil.copy(reqDTO, SysUserDTO.class);
		sysUserService.save(dto);
		return Result.success();
	}

	@RequestLog("修改用户信息")
	@ApiOperation("修改用户信息")
	@PutMapping
	@PreAuthorize("hasAuthority('sys:user:update')")
	public Result<Void> update(@RequestBody SysUserReqDTO reqDTO) {
		SysUserDTO dto = BeanUtil.copy(reqDTO, SysUserDTO.class);
		sysUserService.modifyById(dto);
		return Result.success();
	}

	@RequestLog("修改用户密码")
	@ApiOperation("修改用户密码")
	@PutMapping("password")
	@PreAuthorize("hasAuthority('sys:user:update')")
	public Result<Void> password(@RequestBody PasswordDTO dto) {
		SystemUserDetails user = SystemSecurityUtils.getUserDetails();
		if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
			return Result.error(ErrorCode.System.USER_PASSWORD_ERROR.getCode());
		}
		sysUserService.updatePassword(user.getId(), dto.getNewPassword());
		return Result.success();
	}

	@RequestLog("删除用户")
	@ApiOperation("删除用户")
	@DeleteMapping
	@PreAuthorize("hasAuthority('sys:user:delete')")
	public Result<Void> delete(@RequestBody List<Long> ids) {
		sysUserService.deleteBatchIds(ids);
		return Result.success();
	}
}