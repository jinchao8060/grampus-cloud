package com.vdegree.grampus.auth.modules.security.exception;

import com.vdegree.grampus.auth.code.ErrorCode;
import com.vdegree.grampus.common.core.exception.ApiException;

/**
 * 用户被禁用异常
 *
 * @author Beck
 * @since 2020-12-16
 */
public class UserDisabledException extends ApiException {
	private static final long serialVersionUID = 1791940330757308184L;

	public UserDisabledException() {
		super(ErrorCode.System.USER_DISABLE_ERROR.getCode());
	}
}
