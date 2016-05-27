package com.mlnx.springmvc.entity;

import java.util.Date;

public class Patient{
	private Date birthday;
	private String realName;
	private String userid;
	private Boolean gender;
	private String telephone;
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Boolean getGender() {
		return gender;
	}
	public void setGender(Boolean gender) {
		this.gender = gender;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	@Override
	public String toString() {
		return "Patient [birthday=" + birthday + ", realName=" + realName
				+ ", userid=" + userid + ", gender=" + gender + ", telephone="
				+ telephone + "]";
	}
	
}
