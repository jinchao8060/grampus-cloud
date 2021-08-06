package com.vdegree.grampus.auth.modules.security.exception;

import com.vdegree.grampus.auth.code.ErrorCode;
import com.vdegree.grampus.common.core.exception.ApiException;

/**
 * Token过期异常
 *
 * @author Beck
 * @since 2021-05-07
 */
public class TokenExpiredException extends ApiException {
	private static final long serialVersionUID = -7284332526504876630L;

	public TokenExpiredException() {
		super(ErrorCode.System.TOKEN_EXPIRED_ERROR.getCode());
	}

	public TokenExpiredException(String msg) {
		super(ErrorCode.System.TOKEN_EXPIRED_ERROR.getCode(), msg);
	}
}
