package com.vdegree.grampus.admin.modules.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 操作日志表(LogOperation) 表数据传输对象
 *
 * @author Beck
 * @since 2021-05-31 17:05:41
 */
@Data
@ApiModel("操作日志表")
public class LogOperationDTO implements Serializable {
	private static final long serialVersionUID = -62482078918584463L;
	/**
	 * 数据ID
	 */
	@ApiModelProperty("数据ID")
	private Long id;
	/**
	 * 所属模块
	 */
	@ApiModelProperty("所属模块")
	private String module;
	/**
	 * 操作对象
	 */
	@ApiModelProperty("操作对象")
	private String subject;
	/**
	 * 描述
	 */
	@ApiModelProperty("描述")
	private String description;
	/**
	 * RequestMethod
	 */
	@ApiModelProperty("RequestMethod")
	private String requestMethod;
	/**
	 * RequestUri
	 */
	@ApiModelProperty("RequestUri")
	private String requestUri;
	/**
	 * RequestParam
	 */
	@ApiModelProperty("RequestParam")
	private String requestParam;
	/**
	 * RequestBody
	 */
	@ApiModelProperty("RequestBody")
	private String requestBody;
	/**
	 * 是否成功(0失败 1成功)
	 */
	@ApiModelProperty("是否成功(0失败 1成功)")
	private Boolean successful;
	/**
	 * 类方法
	 */
	@ApiModelProperty("类方法")
	private String classMethod;
	/**
	 * 异常信息
	 */
	@ApiModelProperty("异常信息")
	private String exceptionDetail;
	/**
	 * 请求IP
	 */
	@ApiModelProperty("请求IP")
	private String requestIp;
	/**
	 * 请求开始时间
	 */
	@ApiModelProperty("请求开始时间")
	private LocalDateTime requestStartTime;
	/**
	 * 请求结束时间
	 */
	@ApiModelProperty("请求结束时间")
	private LocalDateTime requestEndTime;
	/**
	 * 请求耗时
	 */
	@ApiModelProperty("请求耗时")
	private Long costTime;
	/**
	 * UA信息
	 */
	@ApiModelProperty("UA信息")
	private String userAgent;
}
