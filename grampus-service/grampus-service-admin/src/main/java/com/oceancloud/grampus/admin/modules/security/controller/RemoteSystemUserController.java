package com.oceancloud.grampus.admin.modules.security.controller;

import com.oceancloud.grampus.admin.modules.security.client.dto.SystemUserDTO;
import com.oceancloud.grampus.admin.modules.system.entity.SysUser;
import com.oceancloud.grampus.admin.modules.system.service.SysUserService;
import com.oceancloud.grampus.framework.core.result.Result;
import com.oceancloud.grampus.framework.core.utils.BeanUtil;
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
		return Result.success(BeanUtil.copyWithConvert(sysUser, SystemUserDTO.class));
	}
}
