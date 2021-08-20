package com.oceancloud.grampus.admin.modules.system.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * SysDictItemQuery
 *
 * @author Beck
 * @since 2021-08-20
 */
@Data
@ApiModel("字典详情")
public class SysDictItemQuery implements Serializable {
	private static final long serialVersionUID = 2654102149758376178L;
	/**
	 * 字典ID
	 */
	@ApiModelProperty(value = "字典ID")
	private Long dictId;
	/**
	 * 字典类型
	 */
	@ApiModelProperty(value = "字典类型(字典ID与类型二选一即可)", example = "入参不需要传入,后台自动根据dictId所属字段类型填充")
	private String dictType;
	/**
	 * 字典标签
	 */
	@ApiModelProperty(value = "字典标签")
	private String dictLabel;
	/**
	 * 字典值
	 */
	@ApiModelProperty(value = "字典值")
	private String dictValue;
}
