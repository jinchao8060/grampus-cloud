package com.vdegree.grampus.admin.modules.security.controller;

import com.vdegree.grampus.admin.modules.security.client.dto.SystemUserDTO;
import com.vdegree.grampus.admin.modules.system.entity.SysUser;
import com.vdegree.grampus.admin.modules.system.service.SysUserService;
import com.vdegree.grampus.common.core.result.Result;
import com.vdegree.grampus.common.core.utils.BeanUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * RemoteSysUserController
 *
 * @author Beck
 * @since 2021-08-05
 */
@RestController
@AllArgsConstructor
@RequestMapping("/rmi/system/user")
public class RemoteSystemUserController {

	private final SysUserService sysUserService;

	@GetMapping("/getSysUserByUserNo")
	public Result<SystemUserDTO> getSysUserByUserNo(@RequestParam("userNo") String userNo) {
		SysUser sysUser = sysUserService.getSysUserByUserNo(userNo);
		return Result.success(BeanUtil.copy(sysUser, SystemUserDTO.class));
	}
}
