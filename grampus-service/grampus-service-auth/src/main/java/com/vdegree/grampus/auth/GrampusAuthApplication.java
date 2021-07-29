package com.vdegree.grampus.auth;

import com.vdegree.grampus.common.gray.annotation.EnableGrayLoadBalancer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

import java.util.TimeZone;

/**
 * Grampus 认证中心
 *
 * @author Beck
 * @since 2021-06-15
 */
//@EnableAuthorizationServer
@EnableGrayLoadBalancer
@EnableFeignClients(basePackages = {"com.vdegree.grampus"})
@SpringBootApplication
public class GrampusAuthApplication {

    public static void main(String[] args) {
        System.setProperty("user.timezone", "UTC");
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(GrampusAuthApplication.class, args);
    }
}
