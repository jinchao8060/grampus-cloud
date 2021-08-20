package com.oceancloud.grampus.admin.modules.system.entity;

import com.oceancloud.grampus.framework.mybatis.annotation.FieldFill;
import com.oceancloud.grampus.framework.mybatis.annotation.TableField;
import com.oceancloud.grampus.framework.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tk.mybatis.mapper.annotation.LogicDelete;

import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * 角色表 实体类
 *
 * @author Beck
 * @since 2020-12-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "sys_role")
public class SysRole extends BaseEntity {
	private static final long serialVersionUID = 748889808530861432L;
	/**
	 * 角色名
	 */
	private String roleName;
	/**
	 * 角色编号
	 */
	private String roleCode;
	/**
	 * 创建者
	 */
	@TableField(fill = FieldFill.INSERT)
	private String createBy;
	/**
	 * 创建日期
	 */
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createDate;
	/**
	 * 更新者
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private String updateBy;
	/**
	 * 更新日期
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateDate;
	/**
	 * 删除标识(0正常 1删除)
	 */
	@LogicDelete
	private Integer delFlag;
}