package com.oceancloud.grampus.gateway.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.List;

/**
 * URIDecoderProperties
 *
 * @author Beck
 * @since 2021-06-10
 */
@Data
@RefreshScope
@ConfigurationProperties(prefix = "decrypt")
public class URIDecoderProperties {
	/**
	 * 服务端私钥
	 */
	private String privateKey;
	/**
	 * 客户端公钥
	 */
	private String publicKey;
	/**
	 * admin端私钥
	 */
	private String adminPrivateKey;
	/**
	 * admin端公钥
	 */
	private String adminPublicKey;
	/**
	 * header字段名
	 */
	private String headerName;
	/**
	 * requestBody的JSON字段(加密后的密文)
	 */
	private String cipherField;
	/**
	 * 过滤URL(强制解析链接)
	 */
	private List<String> urls;
	/**
	 * 响应URL忽略加密(强制关闭链接的响应数据加密)
	 */
	private List<String> responseIgnoreUrls;
}
