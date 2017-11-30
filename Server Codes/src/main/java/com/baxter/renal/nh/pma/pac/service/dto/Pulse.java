package com.baxter.renal.nh.pma.pac.service.dto;

public class Pulse {
	String device;
	double data;
	
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
