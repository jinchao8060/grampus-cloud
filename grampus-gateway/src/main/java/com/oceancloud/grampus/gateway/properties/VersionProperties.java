package com.oceancloud.grampus.gateway.properties;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.oceancloud.grampus.framework.core.utils.JSONUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.Executor;

/**
 * VersionProperties
 *
 * @author Beck
 * @since 2021-06-10
 */
@Slf4j
@Component
public class VersionProperties {

	private static final String VERSION_DATA_ID = "version.json";

	@Getter
	private Map versionInfo;

	public VersionProperties(NacosConfigProperties nacosConfigProperties) throws NacosException {
		NacosConfigManager nacosConfigManager = new NacosConfigManager(nacosConfigProperties);
		versionInfo = JSONUtil.readValue(nacosConfigManager.getConfigService()
				.getConfig(VERSION_DATA_ID, nacosConfigProperties.getGroup(), 300000), Map.class);
		new NacosConfigManager(nacosConfigProperties).getConfigService().addListener(VERSION_DATA_ID, nacosConfigProperties.getGroup(), new Listener() {
			@Override
			public Executor getExecutor() {
				return null;
			}

			@Override
			public void receiveConfigInfo(String json) {
				versionInfo = JSONUtil.readValue(json, Map.class);
			}
		});
	}
}
