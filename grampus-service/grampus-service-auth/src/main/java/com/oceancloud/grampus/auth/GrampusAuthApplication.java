package com.oceancloud.grampus.auth;

import com.oceancloud.grampus.framework.oauth2.annotation.EnableGrampusAuthorizationServer;
import com.oceancloud.grampus.framework.gray.annotation.EnableGrayLoadBalancer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.TimeZone;

/**
 * Grampus 认证中心
 *
 * @author Beck
 * @since 2021-06-15
 */
@EnableGrampusAuthorizationServer
//@EnableGrayLoadBalancer
@EnableFeignClients(basePackages = {"com.oceancloud.grampus"})
@SpringBootApplication
public class GrampusAuthApplication {

    public static void main(String[] args) {
        System.setProperty("user.timezone", "UTC");
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(GrampusAuthApplication.class, args);
    }
}
