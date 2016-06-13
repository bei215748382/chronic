package com.mlnx.chronic.vo;

import java.util.Date;

import com.mlnx.chronic.entity.TAddress;
import com.mlnx.chronic.entity.THospital;
import com.mlnx.chronic.entity.TUser;

public class DocVo {
	
	private Integer id;//编号

	private TUser user;//用户手机号，密码

	private String name;//名字

	private String sex;//性别

	private Date birthday;//生日

	private String office;//科室

	private String title;//职称

	private String skill;//特长

	private TAddress address;//医生地址

	private String pic;//头像

	private THospital hospital;//医院

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

	public TUser getUser() {
		return user;
	}

	public void setUser(TUser user) {
		this.user = user;
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

	public TAddress getAddress() {
		return address;
	}

	public void setAddress(TAddress address) {
		this.address = address;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public THospital getHospital() {
		return hospital;
	}

	public void setHospital(THospital hospital) {
		this.hospital = hospital;
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
		return "DocVo [id=" + id + ", user=" + user + ", name=" + name
				+ ", sex=" + sex + ", birthday=" + birthday + ", office="
				+ office + ", title=" + title + ", skill=" + skill
				+ ", address=" + address + ", pic=" + pic + ", hospital="
				+ hospital + ", background=" + background + ", achievement="
				+ achievement + ", experience=" + experience
				+ ", satisfaction=" + satisfaction + "]";
	}

}
