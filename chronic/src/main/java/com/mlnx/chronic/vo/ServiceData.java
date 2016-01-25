package com.mlnx.chronic.vo;

public class ServiceData {
	
	private int user_id;//用户id
	
	private String name;//医生名字
	
	private String pic;//医生头像
	
	private Double sum;// 服务的总次数
	
	private Double use_device_count;//使用设备次数
	
	private Double rate;//使用设备率
	
	private Double clinic_count;//门诊次数
	
	private Double clinic_device_count;// 门诊使用设备次数
	
	private Double clinic_rate;//门诊使用率
	
	private Double callback_count;//回访次数
	
	private Double callback_device_count;// 回访使用设备次数
	
	private Double callback_rate;//回访使用率

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Double getSum() {
		return sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

	public Double getUse_device_count() {
		return use_device_count;
	}

	public void setUse_device_count(Double use_device_count) {
		this.use_device_count = use_device_count;
	}

	public Double getRate() {
		return use_device_count/sum;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getClinic_count() {
		return clinic_count;
	}

	public void setClinic_count(Double clinic_count) {
		this.clinic_count = clinic_count;
	}

	public Double getClinic_device_count() {
		return clinic_device_count;
	}

	public void setClinic_device_count(Double clinic_device_count) {
		this.clinic_device_count = clinic_device_count;
	}

	public Double getClinic_rate() {
		return clinic_device_count/clinic_count;
	}

	public void setClinic_rate(Double clinic_rate) {
		this.clinic_rate = clinic_rate;
	}

	public Double getCallback_count() {
		return callback_count;
	}

	public void setCallback_count(Double callback_count) {
		this.callback_count = callback_count;
	}

	public Double getCallback_device_count() {
		return callback_device_count;
	}

	public void setCallback_device_count(Double callback_device_count) {
		this.callback_device_count = callback_device_count;
	}

	public Double getCallback_rate() {
		return callback_device_count/callback_count;
	}

	public void setCallback_rate(Double callback_rate) {
		this.callback_rate = callback_rate;
	}

}
