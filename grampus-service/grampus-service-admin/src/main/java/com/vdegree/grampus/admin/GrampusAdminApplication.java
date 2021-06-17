package com.vdegree.grampus.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

/**
 * Grampus System
 *
 * @author Beck
 * @since 2021-06-15
 */
@SpringBootApplication
public class GrampusAdminApplication {

	public static void main(String[] args) {
		System.setProperty("user.timezone", "UTC");
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		SpringApplication.run(GrampusAdminApplication.class, args);
	}
}
