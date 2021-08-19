package com.oceancloud.grampus.admin.modules.system.log;

import com.oceancloud.grampus.admin.modules.system.entity.LogOperation;
import com.oceancloud.grampus.admin.modules.system.service.LogOperationService;
import com.oceancloud.grampus.framework.core.utils.BeanUtil;
import com.oceancloud.grampus.framework.log.event.LogEvent;
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

	@Async
	@Order
	@EventListener(LogEvent.class)
	public void onApplicationEvent(LogEvent event) {
		LogOperation logOperation = BeanUtil.copy(event, LogOperation.class);
		logOperation.setSubject(null);
		logOperationService.insert(logOperation);
	}
}
