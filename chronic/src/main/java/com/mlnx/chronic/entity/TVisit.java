package com.mlnx.chronic.entity;

import java.util.Date;

public class TVisit {

	private Integer id;

	private Integer patientId;

	private Date date;

	private Integer doctorId;
	
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	@Override
	public String toString() {
		return "TVisit [id=" + id + ", patientId=" + patientId + ", date="
				+ date + ", doctorId=" + doctorId + "]";
	}

}