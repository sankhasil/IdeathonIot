/*
 * BAXTER CONFIDENTIAL - Highly Restricted: Do not distribute without prior approval
 *
 * Project: New Horizons
 *
 * Copyright Â© 2017 Baxter Healthcare Corporation. All rights reserved.
 */

package com.baxter.renal.nh.pma.pac.service.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.baxter.renal.nh.pma.pac.service.constants.ControllerMessageConstants;
import com.baxter.renal.nh.pma.pac.service.dto.PACLookup;
import com.baxter.renal.nh.pma.pac.service.exception.PulseNotStoredException;

/**
 * PAC Lookup Repository
 * @author shetev
 *
 */

@Repository
public class PACLookupRepository {
	
	private final String queryPacInfoFromPac = "SELECT * FROM GLOBAL_ACTIVATION_LOOKUP WHERE ACTIVATION_CODE = ?";
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	/**
	 * Retrieves patientid and region code given pac
	 * @param pac Patient activation code
	 * @return PACLookup object containing pac, patientId and regionCode
	 * @throws PulseNotStoredException - If pac not found or data access problem
	 */
	public PACLookup getPacInfo(String pac){
		try{
			return jdbcTemplate.queryForObject(queryPacInfoFromPac, new Object[] {pac}, new BeanPropertyRowMapper<PACLookup>(PACLookup.class));
		}
		catch(IncorrectResultSizeDataAccessException e){
			throw new PulseNotStoredException(ControllerMessageConstants.ERROR_PAC_NOT_FOUND + pac);
		}
		catch(DataAccessException e){
			throw new PulseNotStoredException(ControllerMessageConstants.ERROR_DATA_ACCESS + pac);
		}
	}
}
