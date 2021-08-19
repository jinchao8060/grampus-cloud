package com.oceancloud.grampus.admin.modules.system.dto;

import com.oceancloud.grampus.framework.core.utils.tree.TreeNode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 系统部门DTO
 *
 * @author Beck
 * @since 2020-12-03 20:06:54
 */
@Data
@ApiModel("系统部门")
public class SysDeptDTO extends TreeNode<SysDeptDTO> {
	private static final long serialVersionUID = -4286031575713376186L;
	/**
	 * 数据ID
	 */
	@ApiModelProperty("数据ID")
	private Long id;
	/**
	 * 父级部门ID
	 */
	@ApiModelProperty(value = "父级部门ID", example = "顶级结点则不传或传入0")
	private Long parentId;
	/**
	 * 部门名称
	 */
	@ApiModelProperty(value = "部门名称", required = true)
	private String deptName;
	/**
	 * 排序
	 */
	@ApiModelProperty("排序")
	private Integer sort;
	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
	private LocalDateTime createDate;
}