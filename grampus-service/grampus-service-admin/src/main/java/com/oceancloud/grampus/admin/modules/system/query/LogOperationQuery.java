package com.oceancloud.grampus.admin.modules.system.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * LogOperationQuery
 *
 * @author Beck
 * @since 2021-08-20
 */
@Data
@ApiModel("操作日志表")
public class LogOperationQuery implements Serializable {
	private static final long serialVersionUID = 767638546842396520L;
	/**
	 * 所属模块
	 */
	@ApiModelProperty("所属模块")
	private String module;
	/**
	 * 是否成功(true、false)
	 */
	@ApiModelProperty("是否成功(true、false)")
	private Boolean successful;
}
