package com.oceancloud.grampus.admin.modules.system.entity;

import com.oceancloud.grampus.framework.mybatis.annotation.FieldFill;
import com.oceancloud.grampus.framework.mybatis.annotation.TableField;
import com.oceancloud.grampus.framework.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * 用户登陆日志表 实体类
 *
 * @author Beck
 * @since 2020-12-09 19:48:58
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "sys_log_login")
public class SysLogLogin extends BaseEntity {
	private static final long serialVersionUID = -94189723127366413L;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 用户号
	 */
	private String userNo;
	/**
	 * 浏览器信息
	 */
	private String userAgent;
	/**
	 * 用户操作(1登录 2退登 3关闭应用)
	 */
	private String operation;
	/**
	 * 操作IP
	 */
	private String operateIp;
	/**
	 * 状态(0失败 1成功)
	 */
	private Integer status;
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
}