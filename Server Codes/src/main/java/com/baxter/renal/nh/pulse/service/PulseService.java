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
import com.baxter.renal.nh.pulse.service.dao.PACLookupRepository;
import com.baxter.renal.nh.pulse.service.dto.PACLookup;
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
	private PACLookupRepository pacLookupRepository;
	
	/**
	 * Given a PAC, calls repository and retrieves info
	 * @param pac Patient Activation Code to be searched
	 * @return PACLookup object containing pac, patientId and regionCode
	 * @throws InvalidRequestException - If request is not valid
	 */
	public PACLookup getPacInfo(String pac){
		
		if(StringUtils.isEmpty(pac)){
			throw new InvalidRequestException(ControllerMessageConstants.ERROR_INVALID_REQUEST);
		}
		return pacLookupRepository.getPacInfo(pac);
	}
}
