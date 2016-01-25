package com.mlnx.chronic.vo;

import com.mlnx.chronic.entity.THospital;

public class DeviceVo {
	private Integer id;

	private String name;

	private THospital hospital;

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

	public THospital getHospital() {
		return hospital;
	}

	public void setHospital(THospital hospital) {
		this.hospital = hospital;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@Override
	public String toString() {
		return "DeviceVo [id=" + id + ", name=" + name + ", hospital="
				+ hospital + ", deviceId=" + deviceId + "]";
	}

}
