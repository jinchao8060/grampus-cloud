package com.vdegree.grampus.gateway.gray;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * GrayRoutesProperties
 *
 * @author Beck
 * @since 2021-07-01
 */
@Data
@Builder
public class GrayRequestInfo implements Serializable {
	private static final long serialVersionUID = -7984573903396158927L;
	/**
	 * 请求平台
	 */
	private String platform;
	/**
	 * 请求IP
	 */
	private String ip;
	/**
	 * 请求版本号
	 */
	private String version;
	/**
	 * 用户编号
	 */
	private String subject;
	/**
	 * 经度
	 */
	private String lon;
	/**
	 * 纬度
	 */
	private String lat;
}