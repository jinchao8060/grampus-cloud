package com.vdegree.grampus.gateway.gray.rule;

import com.vdegree.grampus.common.core.utils.StringUtil;
import com.vdegree.grampus.gateway.gray.GrayRequestInfo;
import com.vdegree.grampus.gateway.gray.GrayRoutesProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 匹配请求IP
 *
 * @author Beck
 * @since 2021-07-01
 */
@Order(2)
@Component
public class GrayIpRuleMatch implements IGrayRuleMatch {

	@Override
	public boolean isMatch(GrayRoutesProperties.RuleConditionDefinition ruleCondition, GrayRequestInfo requestInfo) {
		String currentIp = requestInfo.getIp();
		String ipStr = ruleCondition.getIp();
		try {
			String[] ipArr = ipStr.split(",");
			for (String ip : ipArr) {
				if (StringUtil.isBlank(ip)) {
					continue;
				}
				String ipSub = ip.replace("*", "");
				boolean isMatch = currentIp.contains(ipSub);
				if (isMatch) {
					return true;
				}
			}
		} catch (Exception ignored) {
			return false;
		}
		return false;
	}
}
