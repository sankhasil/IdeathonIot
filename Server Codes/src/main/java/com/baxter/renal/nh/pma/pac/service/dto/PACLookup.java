/*
 * BAXTER CONFIDENTIAL - Highly Restricted: Do not distribute without prior approval
 *
 * Project: New Horizons
 *
 * Copyright Â© 2017 Baxter Healthcare Corporation. All rights reserved.
 */
package com.baxter.renal.nh.pma.pac.service.dto;

/**
 * PACLookup domain object
 * 
 * @author shetev
 *
 */
public class PACLookup {
	private String activationCode;
	private long patientId;
	private String regionCode;
	
	/**
	 * Constructor
	 */
	public PACLookup() {
		super();
	}

	/**
	 * Constructor
	 * @param activationCode Patient Activation Code to set
	 * @param patientId Patient Id
	 * @param regionCode Region Code
	 */
	public PACLookup(String activationCode, long patientId, String regionCode) {
		super();
		this.activationCode = activationCode;
		this.patientId = patientId;
		this.regionCode = regionCode;
	}
	
	/**
	 * Returns patient activation code
	 * @return activationCode
	 */
	public String getActivationCode() {
		return activationCode;
	}
	
	/**
	 * Sets Patient activation code
	 * @param activationCode
	 */
	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}
	
	/**
	 * Returns patient id
	 * @return patientId
	 */
	public long getPatientId() {
		return patientId;
	}
	
	/**
	 * Sets patient id
	 * @param patientId
	 */
	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}
	
	/**
	 * Returns Region code
	 * @return regionCode
	 */
	public String getRegionCode() {
		return regionCode;
	}
	
	/**
	 * Sets Region Code
	 * @param regionCode
	 */
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	
	/**
	 * Overriding toString method for pretty printing object.
	 */
	public String toSend(){
		return "{ActivationCode: "+ this.activationCode + ", PatientId: " + this.patientId + ", RegionCode: "+ this.regionCode + "}";
	}
	
}
