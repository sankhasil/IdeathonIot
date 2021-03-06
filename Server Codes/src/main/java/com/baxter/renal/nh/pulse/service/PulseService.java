/*
 * BAXTER CONFIDENTIAL - Highly Restricted: Do not distribute without prior approval
 *
 * Project: New Horizons
 *
 * Copyright Â© 2017 Baxter Healthcare Corporation. All rights reserved.
 */

package com.baxter.renal.nh.pulse.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baxter.renal.nh.pulse.service.constants.ControllerMessageConstants;
import com.baxter.renal.nh.pulse.service.dao.PulseRepository;
import com.baxter.renal.nh.pulse.service.dto.Pulse;
import com.baxter.renal.nh.pulse.service.exception.InvalidRequestException;

/**
 * PAC service to retrieve patient id and region code from PAC
 * @author shetev
 *
 */
@Service
public class PulseService {
	
	/**
	 * PACLookupRepository object autowired constructor
	 */
	@Autowired
	private PulseRepository pulseRepository;
	
	/**
	 * Given a PAC, calls repository and retrieves info
	 * @param pac Patient Activation Code to be searched
	 * @return PACLookup object containing pac, patientId and regionCode
	 * @throws InvalidRequestException - If request is not valid
	 */

	public void storePulse(Pulse pulse) {
		if(StringUtils.isEmpty(pulse.getDevice())){
			throw new InvalidRequestException(ControllerMessageConstants.ERROR_INVALID_REQUEST);
		}
		
		pulseRepository.storePulse(pulse);
	}
		
}
