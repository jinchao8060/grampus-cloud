package com.vdegree.grampus.gateway.utils;

import com.vdegree.grampus.gateway.properties.AuthTokenProperties;
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

	public static AuthTokenProperties authProperties;

	/**
	 * Get subject Info.
	 *
	 * @param token token
	 * @return auth info
	 */
	public String getSubject(String token) {
		Claims claims = Jwts.parserBuilder().setSigningKey(authProperties.getSecretKeyBytes()).build()
				.parseClaimsJws(token).getBody();
		return claims.getSubject();
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
