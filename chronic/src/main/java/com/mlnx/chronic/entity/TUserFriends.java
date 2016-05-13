package com.mlnx.chronic.entity;

public class TUserFriends {

	private Integer id;

	private Integer userId;

	private Integer friendId;

	private Integer groupId;//用户组

	private Integer fouce;

	private Integer open;

	private String remark; //添加时候的附带信息

	private Integer confirm;
	
	private String friendRemark;
	
	private Integer identity; //请求者身份，1表示是患者请求添加，2表示是医生请求添加

	public TUserFriends() {

	}

	public TUserFriends(int id, int gourpId) {
		this.userId = id;
		this.groupId = gourpId;
	}

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

	public Integer getFriendId() {
		return friendId;
	}

	public void setFriendId(Integer friendId) {
		this.friendId = friendId;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getFouce() {
		return fouce;
	}

	public void setFouce(Integer fouce) {
		this.fouce = fouce;
	}

	public Integer getOpen() {
		return open;
	}

	public void setOpen(Integer open) {
		this.open = open;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getConfirm() {
		return confirm;
	}

	public void setConfirm(Integer confirm) {
		this.confirm = confirm;
	}

	public String getFriendRemark() {
		return friendRemark;
	}

	public void setFriendRemark(String friendRemark) {
		this.friendRemark = friendRemark;
	}

	public Integer getIdentity() {
		return identity;
	}

	public void setIdentity(Integer identity) {
		this.identity = identity;
	}

	@Override
	public String toString() {
		return "TUserFriends [id=" + id + ", userId=" + userId + ", friendId="
				+ friendId + ", groupId=" + groupId + ", fouce=" + fouce
				+ ", open=" + open + ", remark=" + remark + ", confirm="
				+ confirm + ", friendRemark=" + friendRemark + ", identity="
				+ identity + "]";
	}

}