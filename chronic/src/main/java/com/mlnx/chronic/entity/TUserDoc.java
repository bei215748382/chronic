package com.mlnx.chronic.entity;

import java.util.Date;

public class TUserDoc {
	private Integer id;

	private Integer userId;

	private String name;

	private String sex;

	private Date birthday;

	private String office;

	private String title;

	private String skill;

	private Integer addressId;

	private String pic;

	private Integer hospitalId;

	private String background;

	private String achievement;
	
	private String experience;
	
	private Integer satisfaction;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Integer getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public String getAchievement() {
		return achievement;
	}

	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public Integer getSatisfaction() {
		return satisfaction;
	}

	public void setSatisfaction(Integer satisfaction) {
		this.satisfaction = satisfaction;
	}

	@Override
	public String toString() {
		return "TUserDoc [id=" + id + ", userId=" + userId + ", name=" + name
				+ ", sex=" + sex + ", birthday=" + birthday + ", office="
				+ office + ", title=" + title + ", skill=" + skill
				+ ", addressId=" + addressId + ", pic=" + pic + ", hospitalId="
				+ hospitalId + ", background=" + background + ", achievement="
				+ achievement + ", experience=" + experience
				+ ", satisfaction=" + satisfaction + "]";
	}

}