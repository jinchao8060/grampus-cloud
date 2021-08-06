package com.vdegree.grampus.admin.modules.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统语言表DTO
 *
 * @author Beck
 * @since 2021-06-08 10:42:16
 */
@Data
@ApiModel("系统语言表")
public class SysLanguageDTO implements Serializable {
	private static final long serialVersionUID = -53877326603873018L;
	/**
	 * 数据ID
	 */
	@ApiModelProperty("数据ID")
	private Long id;
	/**
	 * 表名
	 */
	@ApiModelProperty("表名")
	private String tableName;
	/**
	 * 表数据ID
	 */
	@ApiModelProperty("表数据ID")
	private Long tableId;
	/**
	 * 字段名
	 */
	@ApiModelProperty("字段名")
	private String fieldName;
	/**
	 * 字段值
	 */
	@ApiModelProperty("字段值")
	private String fieldValue;
	/**
	 * 语言
	 */
	@ApiModelProperty("语言")
	private String language;
}
