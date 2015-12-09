package com.mlnx.chronic.vo;

public class FriendsInfo {
	
	private Integer id;// 好友列表的id
	private String pic;// 好友头像
	private String mark;// 好友备注
	private Integer uid;// 好友用户的id

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "FriendsInfo [id=" + id + ", pic=" + pic + ", mark=" + mark
				+ ", uid=" + uid + "]";
	}

}
