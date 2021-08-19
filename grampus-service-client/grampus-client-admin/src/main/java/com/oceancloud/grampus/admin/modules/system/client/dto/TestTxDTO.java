package com.oceancloud.grampus.admin.modules.system.client.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (TestTx) 表数据传输对象
 *
 * @author Beck
 * @since 2021-06-21 17:13:37
 */
@Data
@ApiModel("")
public class TestTxDTO implements Serializable {
	private static final long serialVersionUID = 633573118468772827L;
	/**
	 * 数据ID
	 */
	@ApiModelProperty("数据ID")
	private Long id;
	/**
	 * 名称
	 */
	@ApiModelProperty("名称")
	private String name;
	/**
	 * 值
	 */
	@ApiModelProperty("值")
	private String value;
	/**
	 * 时间
	 */
	@ApiModelProperty("时间")
	private Date time;
}
