package com.oceancloud.grampus.admin.modules.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 修改密码DTO
 *
 * @author Beck
 * @since 2021-04-14 20:06:54
 */
@Data
@ApiModel(value = "修改密码")
public class PasswordDTO implements Serializable {
	private static final long serialVersionUID = -4260149891670265974L;
	/**
	 * 原密码
	 */
	@ApiModelProperty(value = "原密码", required = true)
	private String password;
	/**
	 * 新密码
	 */
	@ApiModelProperty(value = "新密码", required = true)
	private String newPassword;
}