package com.vdegree.grampus.admin.modules.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 字典类型DTO
 *
 * @author Beck
 * @since 2021-04-12
 */
@Data
@ApiModel("字典类型")
public class SysDictDTO implements Serializable {
	private static final long serialVersionUID = -3004268085455455303L;
	/**
	 * 数据ID
	 */
	@ApiModelProperty("数据ID")
	private Long id;
	/**
	 * 字典类型
	 */
	@ApiModelProperty(value = "字典类型", required = true)
	private String dictType;
	/**
	 * 字典名称
	 */
	@ApiModelProperty(value = "字典名称", required = true)
	private String dictName;
	/**
	 * 备注
	 */
	@ApiModelProperty("备注")
	private String remark;
	/**
	 * 排序
	 */
	@ApiModelProperty("排序")
	private Integer sort;
	/**
	 * 创建者
	 */
	@ApiModelProperty("创建者")
	private String createBy;
	/**
	 * 创建日期
	 */
	@ApiModelProperty("创建日期")
	private LocalDateTime createDate;
}
