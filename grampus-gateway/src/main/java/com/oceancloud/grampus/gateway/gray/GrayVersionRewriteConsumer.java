package com.oceancloud.grampus.gateway.gray;

import com.oceancloud.grampus.framework.core.utils.StringUtil;
import com.oceancloud.grampus.framework.gray.constant.GrayLoadBalancerConstant;
import com.oceancloud.grampus.gateway.gray.rule.IGrayRuleMatcher;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * GrayVersionRewriteConsumer
 * Version IP Token Lon Lat
 *
 * @author Beck
 * @since 2021-06-30
 */
@Slf4j
@Component
@AllArgsConstructor
@EnableConfigurationProperties(GrayRoutesProperties.class)
public class GrayVersionRewriteConsumer implements Consumer<HttpHeaders> {

	private final GrayRoutesProperties grayRoutesProperties;
	private final List<IGrayRuleMatcher> ruleMatchers;

	@Override
	public void accept(HttpHeaders httpHeaders) {
		if (grayRoutesProperties.getEnabled()) {
			GrayRequestInfo requestInfo = buildRequestInfo(httpHeaders);
			String internalVersion = calculateInternalVersion(requestInfo);
			httpHeaders.add(GrayLoadBalancerConstant.HEADER_INTERNAL_VERSION, internalVersion);
		}
	}

	private String calculateInternalVersion(GrayRequestInfo requestInfo) {
		Map<String, List<GrayRoutesProperties.RuleConditionDefinition>> routes = grayRoutesProperties.getRoutes();
		// 匹配请求所属平台
		if (StringUtil.isBlank(requestInfo.getPlatform())) {
			return requestInfo.getVersion();
		}
		List<GrayRoutesProperties.RuleConditionDefinition> ruleConditions = routes.get(requestInfo.getPlatform());
		for (GrayRoutesProperties.RuleConditionDefinition ruleCondition : ruleConditions) {
			for (IGrayRuleMatcher ruleMatcher : ruleMatchers) {
				if (ruleMatcher.isMatch(ruleCondition, requestInfo)) {
					return ruleCondition.getVersion();
				}
			}
		}
		return calculateDefaultVersion(requestInfo.getPlatform());
	}

	private String calculateDefaultVersion(String platform) {
		String defaultVersion = grayRoutesProperties.getDefaultVersion();
		if (StringUtil.isBlank(defaultVersion)) {
			return null;
		}
		String[] defaultPlatformVersionArr = defaultVersion.split(",");
		if (defaultPlatformVersionArr.length == 0) {
			return null;
		}
		for (String defaultPlatformVersion : defaultPlatformVersionArr) {
			if (StringUtil.isBlank(defaultPlatformVersion)) {
				continue;
			}
			String[] platformVersionArr = defaultPlatformVersion.split("#");
			if (platformVersionArr.length < 2) {
				continue;
			}
			String plat = platformVersionArr[0];
			String version = platformVersionArr[1];
			if (StringUtil.isNotBlank(plat) && plat.equals(platform)) {
				return version;
			}
		}
		return defaultVersion;
	}

	private GrayRequestInfo buildRequestInfo(HttpHeaders httpHeaders) {
		String platform = httpHeaders.getFirst(GrayLoadBalancerConstant.HEADER_INTERNAL_REQUEST_PLATFORM);
		String ip = httpHeaders.getFirst(GrayLoadBalancerConstant.HEADER_INTERNAL_REQUEST_IP);
		String version = httpHeaders.getFirst(GrayLoadBalancerConstant.HEADER_VERSION);
		String subject = httpHeaders.getFirst(GrayLoadBalancerConstant.HEADER_INTERNAL_REQUEST_SUBJECT);
		String lon = httpHeaders.getFirst(GrayLoadBalancerConstant.HEADER_LON);
		String lat = httpHeaders.getFirst(GrayLoadBalancerConstant.HEADER_LAT);
		return GrayRequestInfo.builder().ip(ip).version(version)
				.platform(platform).subject(subject).lon(lon).lat(lat).build();
	}
}
