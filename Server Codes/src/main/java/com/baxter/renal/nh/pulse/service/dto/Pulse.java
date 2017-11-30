package com.baxter.renal.nh.pulse.service.dto;

public class Pulse {
	String device;
	double data;
	
	public Pulse() {
		super();
	}
	public Pulse(String device, double data) {
		super();
		this.device = device;
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
        return String.format("\n\nDevice: " + this.device + "\nData: " + this.data);
    }
	
}
