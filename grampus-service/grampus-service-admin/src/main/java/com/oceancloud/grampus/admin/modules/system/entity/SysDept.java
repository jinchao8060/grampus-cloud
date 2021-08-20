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
 * 部门表 实体类
 *
 * @author Beck
 * @since 2020-12-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "sys_dept")
public class SysDept extends BaseEntity {
	private static final long serialVersionUID = 950431345148076167L;
	/**
	 * 父级部门ID
	 */
	private Long parentId;
	/**
	 * 部门名称
	 */
	private String deptName;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 创建者
	 */
	@TableField(fill = FieldFill.INSERT)
	private String createBy;
	/**
	 * 创建时间
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