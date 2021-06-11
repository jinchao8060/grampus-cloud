package com.vdegree.grampus.gateway.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 不过滤链接
 *
 * @author Beck
 * @since 2021-06-10
 */
@Data
@Component
@RefreshScope
@ConfigurationProperties(prefix = "noauth")
public class NoAuthProperties {

    private List<String> urls;
}
