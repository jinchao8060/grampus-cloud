package com.vdegree.grampus.gateway.properties;

import com.vdegree.grampus.gateway.utils.JwtTokenUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 认证相关的配置
 *
 * @author Beck
 * @since 2020-12-9
 */
@Getter
@Setter
@Configuration
public class AuthTokenProperties implements InitializingBean {
	private static final long serialVersionUID = -6420262325876749514L;

	/**
	 * secret key.
	 */
	@Value("${grampus.auth.token.secret.key:}")
	private String secretKey;

	public byte[] getSecretKeyBytes() {
		return secretKey.getBytes();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		JwtTokenUtil.authProperties = this;
	}
}
