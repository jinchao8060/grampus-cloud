package com.vdegree.grampus.gateway.gray.rule;

import com.vdegree.grampus.common.core.utils.StringUtil;
import com.vdegree.grampus.gateway.gray.GrayRequestInfo;
import com.vdegree.grampus.gateway.gray.GrayRoutesProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 匹配特殊账号
 *
 * @author Beck
 * @since 2021-07-01
 */
@Order(2)
@Component
public class GraySubjectRuleMatcher implements IGrayRuleMatcher {

	@Override
	public boolean isMatch(GrayRoutesProperties.RuleConditionDefinition ruleCondition, GrayRequestInfo requestInfo) {
		String curSubject = requestInfo.getSubject();
		String subjectStr = ruleCondition.getSubject();
		if (StringUtil.isBlank(curSubject) || StringUtil.isBlank(subjectStr)) {
			return false;
		}
		String[] subjectArr = subjectStr.split(",");
		if (subjectArr.length == 0) {
			return false;
		}
		for (String subject : subjectArr) {
			if (StringUtil.isNotBlank(subject)
					&& subject.equals(curSubject)) {
				return true;
			}
		}
		return false;
	}
}
