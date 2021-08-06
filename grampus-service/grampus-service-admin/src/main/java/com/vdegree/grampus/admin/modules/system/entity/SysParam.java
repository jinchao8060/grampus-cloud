package com.vdegree.grampus.admin.modules.system.entity;

import com.vdegree.grampus.common.mybatis.annotation.FieldFill;
import com.vdegree.grampus.common.mybatis.annotation.TableField;
import com.vdegree.grampus.common.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tk.mybatis.mapper.annotation.LogicDelete;

import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * 公共参数表 实体类
 *
 * @author Beck
 * @since 2020-12-09 19:49:53
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "sys_param")
public class SysParam extends BaseEntity {
	private static final long serialVersionUID = -81165682367270817L;
	/**
	 * 参数编码
	 */
	private String code;
	/**
	 * 参数值
	 */
	private String value;
	/**
	 * 备注
	 */
	private String remark;
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