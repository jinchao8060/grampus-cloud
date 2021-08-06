package com.vdegree.grampus.admin.modules.system.log;

import com.vdegree.grampus.admin.modules.system.entity.LogOperation;
import com.vdegree.grampus.admin.modules.security.manager.JwtTokenManager;
import com.vdegree.grampus.admin.modules.system.service.LogOperationService;
import com.vdegree.grampus.common.core.utils.BeanUtil;
import com.vdegree.grampus.common.log.event.LogEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * SysLogEventListener
 *
 * @author Beck
 * @since 2021-05-28
 */
@Slf4j
@Component
@AllArgsConstructor
public class LogEventListener {

	private final LogOperationService logOperationService;
	private final JwtTokenManager jwtTokenManager;

	@Async
	@Order
	@EventListener(LogEvent.class)
	public void onApplicationEvent(LogEvent event) {
		LogOperation logOperation = BeanUtil.copy(event, LogOperation.class);
		logOperation.setSubject(jwtTokenManager.getSubject(event.getAuthorization()));
		logOperationService.insert(logOperation);
	}
}
