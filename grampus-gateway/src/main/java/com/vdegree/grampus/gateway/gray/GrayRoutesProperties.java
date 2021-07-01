package com.vdegree.grampus.gateway.gray;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * GrayRoutesProperties
 *
 * @author Beck
 * @since 2021-07-01
 */
@Data
@Component
@RefreshScope
@ConfigurationProperties(prefix = "gray")
public class GrayRoutesProperties implements Serializable {
	private static final long serialVersionUID = -5194157637384095678L;

	private Map<String, List<RuleConditionDefinition>> routes;

	@Data
	public static class RuleConditionDefinition implements Serializable {
		private static final long serialVersionUID = -8459727868161750895L;
		/**
		 * 规则所属版本号
		 */
		private String version;
		/**
		 * IP规则
		 */
		private String ip;
		/**
		 * 限定用户
		 */
		private String subject;
		/**
		 * 经纬度 格式：经度#纬度#米
		 */
		private String lonlat;
	}
}
