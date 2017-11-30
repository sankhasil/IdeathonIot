/*
 * BAXTER CONFIDENTIAL - Highly Restricted: Do not distribute without prior approval
 *
 * Project: New Horizons
 *
 * Copyright Â© 2017 Baxter Healthcare Corporation. All rights reserved.
 */

package com.baxter.renal.nh.pulse.service.exception;

/**
 * InvalidRequestException Definition
 * @author shetev
 */
public class InvalidRequestException extends RuntimeException{
	private static final long serialVersionUID = 7633172770323555567L;
	private String error;

	/**
	 * Constructor
	 * @param error Error String
	 */
	public InvalidRequestException(String error) {
		super();
		this.setError(error);
	}

	/**
	 * Returns error string
	 * @return error Error string
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
