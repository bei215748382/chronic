package com.mlnx.chronic.entity;

public class TDevice {
    private Integer id;

    private String name;

    private Integer hospitalId;
    
    private String deviceId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@Override
	public String toString() {
		return "TDevice [id=" + id + ", name=" + name + ", hospitalId="
				+ hospitalId + ", deviceId=" + deviceId + "]";
	}

}