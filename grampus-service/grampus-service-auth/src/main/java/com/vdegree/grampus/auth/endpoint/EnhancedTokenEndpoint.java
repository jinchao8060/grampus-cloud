package com.vdegree.grampus.auth.endpoint;

import com.google.common.collect.Maps;
import com.vdegree.grampus.common.core.result.Result;
import com.vdegree.grampus.common.core.utils.StringUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * EnhancedTokenEndpoint
 *
 * @author Beck
 * @since 2021-08-11
 */
@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class EnhancedTokenEndpoint {

	private final TokenEndpoint tokenEndpoint;
	private final TokenStore tokenStore;

	@PostMapping("/oauth/token")
	public Result token(Principal principal, @RequestParam
			Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
		OAuth2AccessToken accessToken;
		accessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();

		Map<String, Object> resultMap = Maps.newLinkedHashMap();
		// token信息
		resultMap.put("access_token", accessToken.getValue());
		resultMap.put("token_type", accessToken.getTokenType());
		resultMap.put("expires_in", accessToken.getExpiresIn());
		resultMap.put("scope", StringUtil.join(accessToken.getScope(), ","));
		resultMap.putAll(accessToken.getAdditionalInformation());
		// 权限信息
		Collection<? extends GrantedAuthority> authorities =
				tokenStore.readAuthentication(accessToken).getUserAuthentication().getAuthorities();
		List<String> list = new ArrayList<>();
		for (GrantedAuthority authority : authorities) {
			list.add(authority.getAuthority());
		}
		resultMap.put("authorities", list);
		return Result.success(resultMap);
	}

}
