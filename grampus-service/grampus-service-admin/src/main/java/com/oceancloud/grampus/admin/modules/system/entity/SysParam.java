package com.oceancloud.grampus.admin.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.oceancloud.grampus.framework.mybatis.annotation.FieldFill;
import com.oceancloud.grampus.framework.mybatis.annotation.TableField;
import com.oceancloud.grampus.framework.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 公共参数表 实体类
 *
 * @author Beck
 * @since 2020-12-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_param")
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
	@TableLogic
	private Integer delFlag;
}