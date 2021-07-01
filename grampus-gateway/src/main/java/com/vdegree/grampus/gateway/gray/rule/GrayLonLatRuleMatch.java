package com.vdegree.grampus.gateway.gray.rule;

import com.vdegree.grampus.common.core.utils.StringUtil;
import com.vdegree.grampus.common.core.utils.geo.GeoUtil;
import com.vdegree.grampus.gateway.gray.GrayRequestInfo;
import com.vdegree.grampus.gateway.gray.GrayRoutesProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 匹配请求经纬度范围
 *
 * @author Beck
 * @since 2021-07-01
 */
@Order(3)
@Component
public class GrayLonLatRuleMatch implements IGrayRuleMatch {

	@Override
	public boolean isMatch(GrayRoutesProperties.RuleConditionDefinition ruleCondition, GrayRequestInfo requestInfo) {
		try {
			String currentLon = requestInfo.getLon();
			String currentLat = requestInfo.getLat();
			String lonlatStr = ruleCondition.getLonlat();
			if (StringUtil.isBlank(lonlatStr)) {
				return false;
			}
			String[] lonlatArr = lonlatStr.split(",");
			for (String lonlat : lonlatArr) {
				String[] lontlatArr = lonlat.split("#");
				String lonStr = lontlatArr[0];
				String latStr = lontlatArr[1];
				String disStr = lontlatArr[2];
				if (StringUtil.isBlank(lonStr) || StringUtil.isBlank(latStr) || StringUtil.isBlank(disStr)) {
					return false;
				}
				double lon = Double.parseDouble(lonStr);
				double lat = Double.parseDouble(latStr);
				double disRange = Double.parseDouble(disStr);

				double lonReq = Double.parseDouble(currentLon);
				double latReq = Double.parseDouble(currentLat);
				double distance = GeoUtil.getDistance(lon, lat, lonReq, latReq);

				if (distance <= disRange) {
					return true;
				}
			}
		} catch (Exception ignored) {
			return false;
		}
		return false;
	}
}
