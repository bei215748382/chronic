package com.mlnx.chronic.vo;

import java.util.Date;

import com.mlnx.chronic.entity.TBloodPressureSetting;

public class UserSetting extends TBloodPressureSetting {
	private Date time;// 最近一次报告时间
	
	private Integer patient_id;

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(Integer patient_id) {
		this.patient_id = patient_id;
	}


}
