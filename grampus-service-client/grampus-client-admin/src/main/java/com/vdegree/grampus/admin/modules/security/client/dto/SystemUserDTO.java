package com.vdegree.grampus.admin.modules.security.client.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * SystemUserDTO
 *
 * @author Beck
 * @since 2021-08-05
 */
@Data
public class SystemUserDTO implements Serializable {
	private static final long serialVersionUID = 8379341533037643650L;
	/**
	 * 数据ID
	 */
	private Long id;
	/**
	 * 员工号
	 */
	private String userNo;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 性别
	 */
	private Integer gender;
	/**
	 * 所属部门ID
	 */
	private Long deptId;
	/**
	 * 是否超级管理员(0普通 1超管)
	 */
	private Integer superAdmin;
	/**
	 * 是否启用(0停用 1启用)
	 */
	private Integer enabled;
	/**
	 * 创建者
	 */
	private String createBy;
	/**
	 * 创建时间
	 */
	private LocalDateTime createDate;
	/**
	 * 更新者
	 */
	private String updateBy;
	/**
	 * 更新时间
	 */
	private LocalDateTime updateDate;
	/**
	 * 删除标记(0正常 1删除)
	 */
	private Integer delFlag;
}
