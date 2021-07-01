package com.vdegree.grampus.admin;

import com.vdegree.grampus.common.gray.annotation.EnableGrayLoadBalancer;
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
@EnableGrayLoadBalancer
@EnableFeignClients(basePackages = {"com.vdegree.grampus"})
@SpringBootApplication
public class GrampusAdminApplication {

	public static void main(String[] args) {
		System.setProperty("user.timezone", "UTC");
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		SpringApplication.run(GrampusAdminApplication.class, args);
	}
}
