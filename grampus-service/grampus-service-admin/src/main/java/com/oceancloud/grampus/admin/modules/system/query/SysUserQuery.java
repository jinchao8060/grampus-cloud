package com.oceancloud.grampus.admin.modules.system.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * SysUserQuery
 *
 * @author Beck
 * @since 2021-08-20
 */
@Data
@ApiModel("系统用户")
public class SysUserQuery implements Serializable {
	private static final long serialVersionUID = 4348801592592671669L;
	/**
	 * 员工号
	 */
	@ApiModelProperty("员工号")
	private String userNo;
	/**
	 * 姓名
	 */
	@ApiModelProperty("姓名")
	private String name;
}
