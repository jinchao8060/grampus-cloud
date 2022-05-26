package com.oceancloud.grampus.admin.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.oceancloud.grampus.framework.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 操作日志表(LogOperation) 表实体类
 *
 * @author Beck
 * @since 2021-05-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("log_operation")
public class LogOperation extends BaseEntity {
	private static final long serialVersionUID = -64891032904391963L;
	/**
	 * 所属模块
	 */
	private String module;
	/**
	 * 操作对象
	 */
	private String subject;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * RequestMethod
	 */
	private String requestMethod;
	/**
	 * RequestUri
	 */
	private String requestUri;
	/**
	 * RequestParam
	 */
	private String requestParam;
	/**
	 * RequestBody
	 */
	private String requestBody;
	/**
	 * 是否成功(0失败 1成功)
	 */
	private Boolean successful;
	/**
	 * 类方法
	 */
	private String classMethod;
	/**
	 * 异常信息
	 */
	private String exceptionDetail;
	/**
	 * 请求IP
	 */
	private String requestIp;
	/**
	 * 请求开始时间
	 */
	private LocalDateTime requestStartTime;
	/**
	 * 请求结束时间
	 */
	private LocalDateTime requestEndTime;
	/**
	 * 请求耗时
	 */
	private Long costTime;
	/**
	 * 请求令牌
	 */
	private String authorization;
	/**
	 * UA信息
	 */
	private String userAgent;
}
