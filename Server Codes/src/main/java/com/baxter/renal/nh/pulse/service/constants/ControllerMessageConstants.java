/*
 * BAXTER CONFIDENTIAL - Highly Restricted: Do not distribute without prior approval
 *
 * Project: New Horizons
 *
 * Copyright Â© 2017 Baxter Healthcare Corporation. All rights reserved.
 */
package com.baxter.renal.nh.pulse.service.constants;

/**
 * Constants for messages related to Controller
 * @author shetev
 *
 */
public final class ControllerMessageConstants {
		/**
		 * Constructor
		 */
		private ControllerMessageConstants() {}

		/**
		 * Controller Messages (static final)
		 */
		public static final String ERROR_PAC_NOT_FOUND = "Patient Activation Code doesn\'t exist or duplicate entry for pac ";

		public static final String ERROR_DATA_ACCESS = "Data access error for Patient Activation Code ";

		public static final String ERROR_INVALID_REQUEST = "Invalid request, check parameters or token payload.";
}
