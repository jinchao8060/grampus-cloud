package com.oceancloud.grampus.gateway.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 错误码
 *
 * @author Beck
 * @since 2021-06-10
 */
public interface ErrorCode {

	/**
	 * 0：成功
	 * -1：未知异常
	 *
	 * 	错误码组成：应用标识+功能域+错误类型+错误编码
	 * 	错误码位数：10位
	 * 	错误码示例：111101P001
	 *	使用规范：只增不改，避免混乱
	 *
	 * 	应用标识(2位字母数字)
	 * 		COMMON: 10
	 * 		APP：11
	 * 		ADMIN：12
	 * 		AGENT：13
	 * 		AGENCY：14
	 *  服务标识(2位数字)
	 *  	网关服务：10
	 *  	认证授权服务：11
	 *  	系统服务：12
	 *  	会员服务：13
	 *  	消费服务：14
	 *  	消息中心：15
	 *  	聊天服务：16
	 *  	社区服务：17
	 *  	活动服务: 18
	 *  	发布商服务：19
	 * 	功能域(2位数字)
	 * 		未分类：00
	 * 		X1功能相关：01
	 * 		X2功能相关：02
	 * 		X3功能相关：03
	 * 		……
	 * 	错误类型(1位字母)
	 * 		参数错误：P
	 * 		业务错误：B
	 * 		系统错误：S
	 * 		网络错误：N
	 * 		数据库错误：D
	 * 		缓存错误：C
	 * 		RPC错误：R
	 * 		文件IO错误：F
	 * 		其他错误：O
	 * 	错误编码(3位数字)
	 * 		自增即可
	 */

	/**
	 * 全局通用错误码
	 */
	@AllArgsConstructor
	enum Global {
		/**
		 * 未知异常错误码
		 */
		UNKNOWN_ERROR_CODE("-1", "UNKNOWN_ERROR_CODE");

		@Getter
		String code;
		@Getter
		String msg;
	}

	/**
	 * 网关服务相关 1010
	 */
	@AllArgsConstructor
	enum Gateway {
		/**
		 * 网关参数异常错误码
		 */
		GATEWAY_PARAMS_DECODE_ERROR("101000P001", "GATEWAY_PARAMS_DECODE_ERROR"),
		/**
		 * 请求拒绝异常错误码
		 */
		GATEWAY_REQUEST_REFUSE_ERROR("101000P002", "GATEWAY_REQUEST_REFUSE_ERROR");

		@Getter
		String code;
		@Getter
		String msg;
	}

	/**
	 * 系统服务相关 1212
	 */
	@AllArgsConstructor
	enum System {
		// ~ ============================ 通用权限管理相关 01 ============================
		/**
		 * 存在子菜单异常
		 */
		SUB_MENU_EXIST("121201B001", "SUB_MENU_EXIST");

		@Getter String code;
		@Getter String msg;
	}
}
