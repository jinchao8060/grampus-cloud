package com.vdegree.grampus.auth.modules.security.exception;

import com.vdegree.grampus.auth.code.ErrorCode;
import com.vdegree.grampus.common.core.exception.ApiException;
import com.vdegree.grampus.common.core.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author Beck
 * @since 2020-12-16
 */
@Slf4j
@RestControllerAdvice
public class SystemExceptionHandler {

	/**
	 * 通用异常
	 */
	@ExceptionHandler(Exception.class)
	public Result<Object> handleException(Exception e) {
		if (e instanceof ApiException) {
			ApiException ex = (ApiException) e;
			Result.error(ex.getCode(), null);
		}
		log.error(e.getMessage(), e);
		return Result.error(ErrorCode.Global.UNKNOWN_ERROR_CODE.getCode(), ErrorCode.Global.UNKNOWN_ERROR_CODE.getMsg());
	}

	/**
	 * 检索用户信息异常
	 */
	@ExceptionHandler(InternalAuthenticationServiceException.class)
	public Result<Object> handleInternalAuthenticationServiceException(InternalAuthenticationServiceException e) {
		if (e.getCause() instanceof ApiException) {
			ApiException ex = (ApiException) e.getCause();
			return Result.error(ex.getCode(), null);
		}
		log.error(e.getMessage(), e);
		return Result.error(ErrorCode.System.USER_PASSWORD_ERROR.getCode(), ErrorCode.System.USER_PASSWORD_ERROR.getMsg());
	}

	/**
	 * 密码错误异常
	 */
	@ExceptionHandler(BadCredentialsException.class)
	public Result<Object> handleBadCredentialsException(BadCredentialsException e) {
		log.error(e.getMessage(), e);
		return Result.error(ErrorCode.System.USER_PASSWORD_ERROR.getCode(), ErrorCode.System.USER_PASSWORD_ERROR.getMsg());
	}

	/**
	 * 接口权限异常
	 */
	@ExceptionHandler(AccessDeniedException.class)
	public Result<Object> handleAccessDeniedException(AccessDeniedException e) {
		log.error(e.getMessage(), e);
		return Result.error(ErrorCode.System.USER_ACCESS_DENIED_ERROR.getCode(), ErrorCode.System.USER_ACCESS_DENIED_ERROR.getMsg());
	}
}
