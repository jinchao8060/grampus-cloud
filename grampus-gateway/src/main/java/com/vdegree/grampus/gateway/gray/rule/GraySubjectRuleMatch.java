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
@Order(1)
@Component
public class GraySubjectRuleMatch implements IGrayRuleMatch {

	@Override
	public boolean isMatch(GrayRoutesProperties.RuleConditionDefinition ruleCondition, GrayRequestInfo requestInfo) {
		try {
			String currentSubject = requestInfo.getSubject();
			String subjectStr = ruleCondition.getSubject();
			if (StringUtil.isBlank(subjectStr)) {
				return false;
			}
			String[] subjectArr = subjectStr.split(",");
			for (String subject : subjectArr) {
				boolean isMatch = subject.equals(currentSubject);
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
