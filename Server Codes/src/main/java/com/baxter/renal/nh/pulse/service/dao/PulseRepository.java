/*
 * BAXTER CONFIDENTIAL - Highly Restricted: Do not distribute without prior approval
 *
 * Project: New Horizons
 *
 * Copyright Â© 2017 Baxter Healthcare Corporation. All rights reserved.
 */

package com.baxter.renal.nh.pulse.service.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.baxter.renal.nh.pulse.service.constants.ControllerMessageConstants;
import com.baxter.renal.nh.pulse.service.dto.Pulse;
import com.baxter.renal.nh.pulse.service.exception.PulseNotStoredException;

/**
 * PAC Lookup Repository
 * @author shetev
 *
 */

@Repository
public class PulseRepository {
	
	//private final String get_trtm_attr_id = "select NH_LOCAL_DEVICEAMIA_1.TRTM_ATTR_SEQ.nextval from dual";
	private final String checkPrePulse = "select count(*) from NH_LOCAL_DEVICEAMIA_1.TRTM_ATTR where ATTR_CD = 'PulsePre' AND PATIENT_ID = ? AND TRTM_ID = 1000060";
	private final String checkPostPulse = "select count(*) from NH_LOCAL_DEVICEAMIA_1.TRTM_ATTR where ATTR_CD = 'PulsePost' AND PATIENT_ID = ? AND TRTM_ID = 1000060";
	private final String insertPostPulse = "insert into NH_LOCAL_DEVICEAMIA_1.TRTM_ATTR (TRTM_ATTR_ID,TRTM_ID,PATIENT_ID,TRTM_TIME,ATTR_CD,TYPE_CD,VALUE_TEXT,VALUE_INT,VALUE_TIME,VALUE_DEC,VALUE_CD,VALUE_FLAG,VALUE_DOMAIN_CD,VALUE_UID,CREATE_TS,CREATE_USERID,UPDATE_TS,UPDATE_USERID) values (NH_LOCAL_DEVICEAMIA_1.TRTM_ATTR_SEQ.nextval,?,?,to_timestamp('27-OCT-17 08.20.48.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'),'PulsePost','DECIMAL',null,null,null,?,null,null,null,null,to_timestamp('20-NOV-17 02.56.23.450000000 PM','DD-MON-RR HH.MI.SSXFF AM'),-1,to_timestamp('20-NOV-17 02.56.23.450000000 PM','DD-MON-RR HH.MI.SSXFF AM'),-1)";
	private final String insertPrePulse = "insert into NH_LOCAL_DEVICEAMIA_1.TRTM_ATTR (TRTM_ATTR_ID,TRTM_ID,PATIENT_ID,TRTM_TIME,ATTR_CD,TYPE_CD,VALUE_TEXT,VALUE_INT,VALUE_TIME,VALUE_DEC,VALUE_CD,VALUE_FLAG,VALUE_DOMAIN_CD,VALUE_UID,CREATE_TS,CREATE_USERID,UPDATE_TS,UPDATE_USERID) values (NH_LOCAL_DEVICEAMIA_1.TRTM_ATTR_SEQ.nextval,?,?,to_timestamp('27-OCT-17 08.20.48.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'),'PulsePre','DECIMAL',null,null,null,?,null,null,null,null,to_timestamp('20-NOV-17 02.56.23.450000000 PM','DD-MON-RR HH.MI.SSXFF AM'),-1,to_timestamp('20-NOV-17 02.56.23.450000000 PM','DD-MON-RR HH.MI.SSXFF AM'),-1)";
	private final String getPAC = "select PATIENT_ID from NH_LOCAL_GLBL_DEVICECORE_1.GLOBAL_ACTIVATION_LOOKUP where ACTIVATION_CODE = ?";
	private final long trtm_id = 1000060;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	/**
	 * Retrieves patientid and region code given pac
	 * @param pac Patient activation code
	 * @return PACLookup object containing pac, patientId and regionCode
	 * @throws PulseNotStoredException - If pac not found or data access problem
	 */

	public void storePulse(Pulse pulse) {
		try{
			long patientId = this.getPatientIdFromPac(pulse.getPatientActivationCode());
			
			if(pulse.getPulsePrePost().equalsIgnoreCase("pre")){
				int count = jdbcTemplate.queryForObject(checkPrePulse, new Object[] {patientId}, Integer.class);
				if(count == 0){
					jdbcTemplate.update(insertPrePulse, new Object[] {trtm_id, patientId, Math.round(pulse.getData())});
				}
			}
			else{
				int count = jdbcTemplate.queryForObject(checkPostPulse, new Object[] {patientId}, Integer.class);
				if(count == 0){
					jdbcTemplate.update(insertPostPulse, new Object[] {trtm_id, patientId, Math.round(pulse.getData())});
				}
			}
			
			
		}
		catch(IncorrectResultSizeDataAccessException e){
			throw new PulseNotStoredException(ControllerMessageConstants.ERROR_PULSE_NOT_STORED + pulse);
		}
		catch(DataAccessException e){
			throw new PulseNotStoredException(ControllerMessageConstants.ERROR_DATA_ACCESS + pulse);
		}
	}

	public long getPatientIdFromPac(String pac) {
		long patientId=0;
		try{
			patientId = jdbcTemplate.queryForObject(getPAC, new Object[]{pac}, Long.class);
		}
		catch(IncorrectResultSizeDataAccessException e){
			throw new PulseNotStoredException(ControllerMessageConstants.ERROR_PULSE_NOT_STORED + patientId);
		}
		catch(DataAccessException e){
			throw new PulseNotStoredException(ControllerMessageConstants.ERROR_DATA_ACCESS + patientId);
		}
		return patientId;
	}
}
