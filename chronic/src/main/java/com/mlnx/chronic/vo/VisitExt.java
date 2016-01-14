package com.mlnx.chronic.vo;

import java.util.Date;
import java.util.List;


public class VisitExt {
	
	private Integer id;
	
	private Integer patientId;

	private Date date;

	private Integer doctorId;

	private List<Integer> tests;
	
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

	public List<Integer> getTests() {
		return tests;
	}

	public void setTests(List<Integer> tests) {
		this.tests = tests;
	}

	@Override
	public String toString() {
		return "VisitExt [id=" + id + ", patientId=" + patientId + ", date="
				+ date + ", doctorId=" + doctorId + ", tests=" + tests + "]";
	}

}
