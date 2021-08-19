package com.oceancloud.grampus.gateway.gray.rule;

import com.oceancloud.grampus.gateway.gray.GrayRequestInfo;
import com.oceancloud.grampus.gateway.gray.GrayRoutesProperties;

/**
 * IGrayRuleMatch 顺序：GrayVersionRuleMatcher -> GraySubjectRuleMatch -> GrayIpRuleMatch -> GrayLonLatRuleMatch
 *
 * @author Beck
 * @since 2021-07-01
 */
public interface IGrayRuleMatcher {

	/**
	 * 是否匹配规则
	 *
	 * @param ruleCondition 当前规则
	 * @param requestInfo   请求信息
	 * @return 是否匹配
	 */
	boolean isMatch(GrayRoutesProperties.RuleConditionDefinition ruleCondition, GrayRequestInfo requestInfo);
}
