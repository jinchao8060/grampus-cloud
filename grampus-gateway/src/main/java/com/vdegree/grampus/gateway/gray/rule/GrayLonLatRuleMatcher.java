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
public class GrayLonLatRuleMatcher implements IGrayRuleMatcher {

	@Override
	public boolean isMatch(GrayRoutesProperties.RuleConditionDefinition ruleCondition, GrayRequestInfo requestInfo) {
		String curLon = requestInfo.getLon();
		String curLat = requestInfo.getLat();
		String lonlatStr = ruleCondition.getLonlat();
		if (StringUtil.isBlank(curLon) || StringUtil.isBlank(curLat)
				|| StringUtil.isBlank(lonlatStr)) {
			return false;
		}

		String[] lonlatArr = lonlatStr.split(",");
		if (lonlatArr.length == 0) {
			return false;
		}
		for (String lonlat : lonlatArr) {
			if (StringUtil.isBlank(lonlat)) {
				continue;
			}

			String[] lontlatArr = lonlat.split("#");
			if (lontlatArr.length < 3) {
				continue;
			}

			String lonStr = lontlatArr[0];
			String latStr = lontlatArr[1];
			String disStr = lontlatArr[2];
			if (StringUtil.isBlank(lonStr) || StringUtil.isBlank(latStr)
					|| StringUtil.isBlank(disStr)) {
				continue;
			}

			try {
				double lon = Double.parseDouble(lonStr);
				double lat = Double.parseDouble(latStr);
				double disRange = Double.parseDouble(disStr);

				double lonReq = Double.parseDouble(curLon);
				double latReq = Double.parseDouble(curLat);
				double distance = GeoUtil.getDistance(lon, lat, lonReq, latReq);

				if (distance <= disRange) {
					return true;
				}
			} catch (NumberFormatException ignored) {
			}
		}
		return false;
	}
}
