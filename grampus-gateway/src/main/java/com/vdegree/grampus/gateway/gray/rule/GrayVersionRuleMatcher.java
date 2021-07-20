package com.vdegree.grampus.gateway.gray.rule;

import com.vdegree.grampus.common.core.utils.StringUtil;
import com.vdegree.grampus.gateway.gray.GrayRequestInfo;
import com.vdegree.grampus.gateway.gray.GrayRoutesProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 匹配版本号
 *
 * @author Beck
 * @since 2021-07-19
 */
@Order(1)
@Component
public class GrayVersionRuleMatcher implements IGrayRuleMatcher {

	@Override
	public boolean isMatch(GrayRoutesProperties.RuleConditionDefinition ruleCondition, GrayRequestInfo requestInfo) {
		String curVersion = requestInfo.getVersion();
		return StringUtil.isNotBlank(ruleCondition.getVersion())
				&& ruleCondition.getVersion().equals(curVersion);
	}
}
