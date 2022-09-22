package com.oceancloud.grampus.admin.modules.system.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * SysParamQuery
 *
 * @author Beck
 * @since 2021-08-20
 */
@Data
@ApiModel("系统参数")
public class SysParamQuery implements Serializable {
	private static final long serialVersionUID = 5849555991146805823L;
	/**
	 * 参数编码
	 */
	@ApiModelProperty(value = "参数编码")
	private String code;
}
