package com.vdegree.grampus.gateway;

import com.vdegree.grampus.common.gray.annotation.EnableGrayLoadBalancer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

/**
 * Grampus API网关
 *
 * @author Beck
 * @since 2021-06-10
 */
@EnableGrayLoadBalancer
@SpringBootApplication
public class GrampusGatewayApplication {

	public static void main(String[] args) {
		System.setProperty("csp.sentinel.app.type", "1");
		System.setProperty("user.timezone", "UTC");
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		SpringApplication.run(GrampusGatewayApplication.class, args);
	}
}