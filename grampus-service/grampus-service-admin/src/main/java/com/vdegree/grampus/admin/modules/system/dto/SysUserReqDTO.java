package com.vdegree.grampus.admin.modules.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * SysUserReqDTO
 *
 * @author Beck
 * @since 2021-06-02
 */
@ApiModel("系统用户")
@Data
public class SysUserReqDTO implements Serializable {
	private static final long serialVersionUID = 1318536021225511284L;
	/**
	 * 数据ID
	 */
	@ApiModelProperty("数据ID")
	private Long id;
	/**
	 * 员工号
	 */
	@ApiModelProperty(value = "员工号", required = true)
	private String userNo;
	/**
	 * 姓名
	 */
	@ApiModelProperty(value = "姓名", required = true)
	private String name;
	/**
	 * 密码
	 */
	@ApiModelProperty(value = "密码", required = true)
	private String password;
	/**
	 * 性别
	 */
	@ApiModelProperty(value = "性别", required = true)
	private Integer gender;
	/**
	 * 所属部门ID
	 */
	@ApiModelProperty(value = "所属部门ID", required = true)
	private Long deptId;
	/**
	 * 是否启用(0停用 1启用)
	 */
	@ApiModelProperty(value = "是否启用(0停用 1启用)", required = true)
	private Integer enabled;
	/**
	 * 用户所属角色列表
	 */
	@ApiModelProperty(value = "用户所属角色列表", required = true)
	private List<Long> roleIdList;
}
