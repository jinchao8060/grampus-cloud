package com.oceancloud.grampus.admin.modules.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 字典详情DTO
 *
 * @author Beck
 * @since 2021-04-13
 */
@Data
@ApiModel("字典详情")
public class SysDictItemDTO implements Serializable {
	private static final long serialVersionUID = -8288240035977175413L;
	/**
	 * 数据ID
	 */
	@ApiModelProperty("数据ID")
	private Long id;
	/**
	 * 字典ID
	 */
	@ApiModelProperty(value = "字典ID", required = true)
	private Long dictId;
	/**
	 * 字典类型
	 */
	@ApiModelProperty(value = "字典类型", example = "入参不需要传入,后台自动根据dictId所属字段类型填充")
	private String dictType;
	/**
	 * 字典标签
	 */
	@ApiModelProperty(value = "字典标签", required = true)
	private String dictLabel;
	/**
	 * 字典值
	 */
	@ApiModelProperty(value = "字典值", required = true)
	private String dictValue;
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
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
	private LocalDateTime createDate;
	/**
	 * 更新者
	 */
	@ApiModelProperty("更新者")
	private String updateBy;
	/**
	 * 更新时间
	 */
	@ApiModelProperty("更新时间")
	private LocalDateTime updateDate;
}
