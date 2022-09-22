package com.oceancloud.grampus.admin.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.oceancloud.grampus.framework.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统语言表 表实体类
 *
 * @author Beck
 * @since 2021-06-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_language")
public class SysLanguage extends BaseEntity {
	private static final long serialVersionUID = 435915333127102526L;
	/**
	 * 数据ID
	 */
	private Long id;
	/**
	 * 表名
	 */
	private String tableName;
	/**
	 * 表数据ID
	 */
	private Long tableId;
	/**
	 * 字段名
	 */
	private String fieldName;
	/**
	 * 字段值
	 */
	private String fieldValue;
	/**
	 * 语言
	 */
	private String language;
}
