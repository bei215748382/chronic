package com.mlnx.chronic.vo;

public class ServiceDeviceData {
	
	private int id;//设备id
	
	private String device_name;//设备名称
	
	private String device_id;//设备出厂编号
	
	private Double device_count;// 设备使用次数
	
	private Double device_use_rate;//设备使用率

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDevice_name() {
		return device_name;
	}

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public Double getDevice_count() {
		return device_count;
	}

	public void setDevice_count(Double device_count) {
		this.device_count = device_count;
	}

	public Double getDevice_use_rate() {
		
		return device_use_rate;
	}

	@Override
	public String toString() {
		return "ServiceDeviceData [id=" + id + ", device_name=" + device_name
				+ ", device_id=" + device_id + ", device_count=" + device_count
				+ "]";
	}
	
}
