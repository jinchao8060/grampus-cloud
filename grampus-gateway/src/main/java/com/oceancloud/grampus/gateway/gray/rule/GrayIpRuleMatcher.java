package com.oceancloud.grampus.gateway.gray.rule;

import com.oceancloud.grampus.framework.core.utils.StringUtil;
import com.oceancloud.grampus.gateway.gray.GrayRequestInfo;
import com.oceancloud.grampus.gateway.gray.GrayRoutesProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 匹配请求IP
 *
 * @author Beck
 * @since 2021-07-01
 */
@Order(3)
@Component
public class GrayIpRuleMatcher implements IGrayRuleMatcher {

	@Override
	public boolean isMatch(GrayRoutesProperties.RuleConditionDefinition ruleCondition, GrayRequestInfo requestInfo) {
		String curtIp = requestInfo.getIp();
		String ipStr = ruleCondition.getIp();
		if (StringUtil.isBlank(curtIp) || StringUtil.isBlank(ipStr)) {
			return false;
		}
		String[] ipArr = ipStr.split(",");
		if (ipArr.length == 0) {
			return false;
		}
		for (String ip : ipArr) {
			if (StringUtil.isBlank(ip)) {
				continue;
			}
			String ipSub = ip.replace("*", "");
			if (curtIp.contains(ipSub)) {
				return true;
			}
		}
		return false;
	}
}
