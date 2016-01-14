package com.mlnx.chronic.entity;

import java.util.Date;

public class TUserExt {
	private Integer id;

	private String name;

	private Integer age;

	private String pic;

	private Integer userId;

	private String sex;

	private Integer patientId;// 病人id，一个用户详细信息对应一个病人
	
	private Integer height;//用户身高
	
	private Integer weight;//用户体重
	
	private Integer pregnant;//是否为孕妇
	
	private Date birthday;//生日
	
	private String nickname;//昵称

	public TUserExt() {

	}

	public TUserExt(int patientId, int userId) {
		this.patientId = patientId;
		this.userId = userId;
	}

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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "TUserExt [id=" + id + ", name=" + name + ", age=" + age
				+ ", pic=" + pic + ", userId=" + userId + ", sex=" + sex
				+ ", patientId=" + patientId + ", height=" + height
				+ ", weight=" + weight + ", pregnant=" + pregnant
				+ ", birthday=" + birthday + ", nickname=" + nickname + "]";
	}

}