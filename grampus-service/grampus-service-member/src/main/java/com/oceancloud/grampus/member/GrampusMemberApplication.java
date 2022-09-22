package com.oceancloud.grampus.member;

import com.oceancloud.grampus.framework.oauth2.annotation.EnableGrampusResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.TimeZone;

/**
 * Grampus Member
 *
 * @author Beck
 * @since 2021-08-25
 */
@EnableGrampusResourceServer
//@EnableGrayLoadBalancer
@EnableFeignClients(basePackages = {"com.oceancloud.grampus"})
@SpringBootApplication
public class GrampusMemberApplication {

	public static void main(String[] args) {
		System.setProperty("user.timezone", "UTC");
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		SpringApplication.run(GrampusMemberApplication.class, args);
	}
}
