package com.oceancloud.grampus.admin.modules.security.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 注册接口入参
 *
 * @author Beck
 * @since 2021-04-09
 */
@Data
@ApiModel("注册接口入参")
public class RegisterReq implements Serializable {
	private static final long serialVersionUID = -8157819258927558166L;
	/**
	 * 员工号
	 */
	@ApiModelProperty("员工号")
	private String userNo;
	/**
	 * 密码
	 */
	@ApiModelProperty("密码")
	private String password;
	/**
	 * 用户名
	 */
	@ApiModelProperty("用户名")
	private String name;
}
