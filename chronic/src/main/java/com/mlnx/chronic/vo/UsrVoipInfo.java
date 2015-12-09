package com.mlnx.chronic.vo;

public class UsrVoipInfo {
	
	private Integer id; // 用户唯一ID

	private String voipAccount; // 容联云账号
	
	private String voipPassword;// 容联云密码
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVoipAccount() {
		return voipAccount;
	}
	public void setVoipAccount(String voipAccount) {
		this.voipAccount = voipAccount;
	}
	public String getVoipPassword() {
		return voipPassword;
	}
	public void setVoipPassword(String voipPassword) {
		this.voipPassword = voipPassword;
	}
	@Override
	public String toString() {
		return "UsrVoipInfo [id=" + id + ", voipAccount=" + voipAccount
				+ ", voipPassword=" + voipPassword + "]";
	}
	
}
