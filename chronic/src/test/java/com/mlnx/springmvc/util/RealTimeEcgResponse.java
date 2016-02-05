package com.mlnx.springmvc.util;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class RealTimeEcgResponse {

	private final RealTimeEcgRequest request;

	private Integer patientId;

	private List<EcgLead> leadList;

	private String token;

	private Integer heartRate;

	private Integer batteryRemaining;

	private Integer signalStrength;

	private Date lastEmergencyCallTime;

	private DataInputStream in;

	private Integer pose;

	private Integer fidelity;

	private Integer connectivity;

	RealTimeEcgResponse(RealTimeEcgRequest request) {
		this.request = request;
	}

	public void setConnectivity(Integer connectivity) {
		this.connectivity = connectivity;
	}

	public void setFidelity(Integer fidelity) {
		this.fidelity = fidelity;
	}

	public void setPose(Integer pose) {
		this.pose = pose;
	}

	public void setIn(DataInputStream in) {
		this.in = in;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public void setLeadList(List<EcgLead> leadList) {
		this.leadList = leadList;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setHeartRate(Integer heartRate) {
		this.heartRate = heartRate;
	}

	public void setBatteryRemaining(Integer batteryRemaining) {
		this.batteryRemaining = batteryRemaining;
	}

	public void setSignalStrength(Integer signalStrength) {
		this.signalStrength = signalStrength;
	}

	public void setLastEmergencyCallTime(Date lastEmergencyCallTime) {
		this.lastEmergencyCallTime = lastEmergencyCallTime;
	}

	String getToken() {

		return token;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public List<EcgLead> getLeads() {
		return leadList;
	}

	public DataInputStream getDataInputStream() throws IOException {
		return in;
	}

	public Integer getHeartRate() {

		return heartRate;
	}

	public Integer getPose() {

		return pose;
	}

	public Integer getBatteryRemaining() {
		return batteryRemaining;
	}

	public Integer getSignalStrength() {
		return signalStrength;
	}

	public Integer getFidelity() {

		return fidelity;
	}

	public Integer getConnectivity() {
		return connectivity;
	}

	public Date getLastEmergencyCallTime() {
		return lastEmergencyCallTime;
	}

	public void close() {
	}

	public RealTimeEcgResponse next() {
		return request.getNextResponse(token);
	}
}
