/*
 * BAXTER CONFIDENTIAL - Highly Restricted: Do not distribute without prior approval
 *
 * Project: New Horizons
 *
 * Copyright Â© 2017 Baxter Healthcare Corporation. All rights reserved.
 */
package com.baxter.renal.nh.pma.pac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application for retrieving Patient info given PAC
 * @author shetev
 *
 */
@SpringBootApplication
public class PulseApplication {

	/**
	 * Starting point for Spring Boot application
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(PulseApplication.class, args);
	}
}