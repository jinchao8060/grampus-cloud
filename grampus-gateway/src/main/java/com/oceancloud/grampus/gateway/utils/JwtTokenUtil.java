package com.oceancloud.grampus.gateway.utils;

import com.oceancloud.grampus.gateway.properties.AuthTokenProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.experimental.UtilityClass;

/**
 * JWT token manager.
 *
 * @author Beck
 * @since 2020-12-5
 */
@UtilityClass
public class JwtTokenUtil {

	private static final String PLATFORM_KEY = "platform";
	public static AuthTokenProperties authProperties;

	/**
	 * Get subject Info.
	 *
	 * @param token token
	 * @return subject info
	 */
	public String getSubject(String token) {
		Claims claims = Jwts.parserBuilder().setSigningKey(authProperties.getSecretKeyBytes()).build()
				.parseClaimsJws(token).getBody();
		return claims.getSubject();
	}

	/**
	 * Get platform Info.
	 *
	 * @param token     token
	 * @return platform
	 */
	public String getPlatform(String token) {
		return getClaim(token, PLATFORM_KEY);
	}

	/**
	 * Get claim Info.
	 *
	 * @param token     token
	 * @param claimName claim name
	 * @return claim info
	 */
	public String getClaim(String token, String claimName) {
		Claims claims = Jwts.parserBuilder().setSigningKey(authProperties.getSecretKeyBytes()).build()
				.parseClaimsJws(token).getBody();
		return claims.get(claimName, String.class);
	}

	/**
	 * validate token.
	 *
	 * @param token token
	 */
	public void validateToken(String token) {
		Jwts.parserBuilder().setSigningKey(authProperties.getSecretKeyBytes()).build().parseClaimsJws(token);
	}

}
