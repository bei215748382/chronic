package com.mlnx.chronic.entity;

import java.util.Date;

public class TPatientBloodPressure {
    private Integer id;

    private Integer patientId;

    private Date dateTime;

    private String deviceId;

    private String diastolic;

    private String systolic;

    private Integer heartRate;

    private String state;

    private String comment;

    private Date timestamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(String diastolic) {
        this.diastolic = diastolic;
    }

    public String getSystolic() {
        return systolic;
    }

    public void setSystolic(String systolic) {
        this.systolic = systolic;
    }

    public Integer getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

	@Override
	public String toString() {
		return "TPatientBloodPressure [id=" + id + ", patientId=" + patientId
				+ ", dateTime=" + dateTime + ", deviceId=" + deviceId
				+ ", diastolic=" + diastolic + ", systolic=" + systolic
				+ ", heartRate=" + heartRate + ", state=" + state
				+ ", comment=" + comment + ", timestamp=" + timestamp + "]";
	}
    
}