package com.oceancloud.grampus.admin;

import com.oceancloud.grampus.framework.oauth2.annotation.EnableGrampusResourceServer;
import com.oceancloud.grampus.framework.gray.annotation.EnableGrayLoadBalancer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.TimeZone;

/**
 * Grampus System
 *
 * @author Beck
 * @since 2021-06-15
 */
@EnableGrampusResourceServer
//@EnableGrayLoadBalancer
@EnableFeignClients(basePackages = {"com.oceancloud.grampus"})
@SpringBootApplication
public class GrampusAdminApplication {

	public static void main(String[] args) {
		System.setProperty("user.timezone", "UTC");
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		SpringApplication.run(GrampusAdminApplication.class, args);
	}
}
