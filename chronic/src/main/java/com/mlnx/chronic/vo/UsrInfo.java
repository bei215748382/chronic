package com.mlnx.chronic.vo;

public class UsrInfo {

	private Integer id; // 用户唯一ID
	
	private Integer patientId;// 病人id，一个用户详细信息对应一个病亿

	private String name; // 姓名
	
	private long birthday; // 出生年月
	
	private String sex; // 性别
	
	private Integer height; // 身高(cm)
	
	private Integer weight; // 体重(kg)
	
	private Integer pregnant; // 孕妇

	private String phone; // 手机号
	
	private String headPicUrl; // 头像

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getBirthday() {
		return birthday;
	}

	public void setBirthday(long birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getPregnant() {
		return pregnant;
	}

	public void setPregnant(Integer pregnant) {
		this.pregnant = pregnant;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHeadPicUrl() {
		return headPicUrl;
	}

	public void setHeadPicUrl(String headPicUrl) {
		this.headPicUrl = headPicUrl;
	}

	@Override
	public String toString() {
		return "UsrInfo [id=" + id + ", patientId=" + patientId + ", name="
				+ name + ", birthday=" + birthday + ", sex=" + sex
				+ ", height=" + height + ", weight=" + weight + ", pregnant="
				+ pregnant + ", phone=" + phone + ", headPicUrl=" + headPicUrl
				+ "]";
	}

}
