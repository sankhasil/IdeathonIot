/*
 * BAXTER CONFIDENTIAL - Highly Restricted: Do not distribute without prior approval
 *
 * Project: New Horizons
 *
 * Copyright Â© 2017 Baxter Healthcare Corporation. All rights reserved.
 */

package com.baxter.renal.nh.pma.pac.service.exception;

/**
 * PacNotFoundException definition
 * @author shetev
 */
public class PulseNotStoredException extends RuntimeException {

	private static final long serialVersionUID = -3462702026687267003L;
	private String error;
	
	/**
	 * Constructor
	 * @param error Error string
	 */
	public PulseNotStoredException(String error) {
		super();
		this.setError(error);
	}

	/**
	 * Returns error string
	 * @return error Error String
	 */
	public String getError() {
		return error;
	}

	/**
	 * Sets error string
	 * @param error
	 */
	public void setError(String error) {
		this.error = error;
	}
}
