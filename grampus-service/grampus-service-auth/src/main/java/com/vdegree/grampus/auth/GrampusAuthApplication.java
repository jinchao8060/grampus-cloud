package com.vdegree.grampus.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

/**
 * Grampus 认证中心
 *
 * @author Beck
 * @since 2021-06-15
 */
@SpringBootApplication
public class GrampusAuthApplication {

    public static void main(String[] args) {
        System.setProperty("user.timezone", "UTC");
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(GrampusAuthApplication.class, args);
    }
}
