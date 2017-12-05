package com.baxter.renal.nh.pulse.service.dto;

public class Pulse {
	private String device;
	private String patientActivationCode;
	private String pulsePrePost;
	private double data;
	
	public String getPatientActivationCode() {
		return patientActivationCode;
	}
	public void setPatientActivationCode(String patientActivationCode) {
		this.patientActivationCode = patientActivationCode;
	}
	public String getPulsePrePost() {
		return pulsePrePost;
	}
	public void setPulsePrePost(String pulsePrePost) {
		this.pulsePrePost = pulsePrePost;
	}
	public Pulse() {
		super();
	}
	
	public Pulse(String device, String patientActivationCode,
			String pulsePrePost, double data) {
		super();
		this.device = device;
		this.patientActivationCode = patientActivationCode;
		this.pulsePrePost = pulsePrePost;
		this.data = data;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public double getData() {
		return data;
	}
	public void setData(double data) {
		this.data = data;
	}
	@Override
    public String toString() {
        return String.format("\nDevice: " + this.device + "\npatientActivationCode: "+this.patientActivationCode +"\npulsePrePost: "+this.pulsePrePost+"\nData: " + this.data);
    }
	
}
