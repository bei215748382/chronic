package com.mlnx.chronic.entity;

import java.util.Date;

public class TService {
	
    private Integer id;

    private Date start;

    private Date end;

    private Integer userId;

    private Integer patientId;

    private Integer type;

    private Integer deviceId;
    
    private Integer addressId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	@Override
	public String toString() {
		return "TService [id=" + id + ", start=" + start + ", end=" + end
				+ ", userId=" + userId + ", patientId=" + patientId + ", type="
				+ type + ", deviceId=" + deviceId + ", addressId=" + addressId
				+ "]";
	}
}