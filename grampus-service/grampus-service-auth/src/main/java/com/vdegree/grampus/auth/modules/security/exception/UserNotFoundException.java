package com.vdegree.grampus.auth.modules.security.exception;

import com.vdegree.grampus.auth.code.ErrorCode;
import com.vdegree.grampus.common.core.exception.ApiException;

/**
 * 用户不存在异常
 *
 * @author Beck
 * @since 2020-12-16
 */
public class UserNotFoundException extends ApiException {
	private static final long serialVersionUID = 5186572331502903237L;

	public UserNotFoundException() {
		super(ErrorCode.System.USER_NOT_EXISTED.getCode());
	}
}
