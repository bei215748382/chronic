package com.mlnx.chronic.vo;

public class RegistUser {
	private String phone;
	private String password;
	private Integer code;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "RegistUser [phone=" + phone + ", password=" + password
				+ ", code=" + code + "]";
	}

}
