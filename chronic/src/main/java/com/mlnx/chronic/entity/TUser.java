package com.mlnx.chronic.entity;

import java.util.Date;

public class TUser {
    private Integer id;

    private String phone;

    private String password;

    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

	@Override
	public String toString() {
		return "TUser [id=" + id + ", phone=" + phone + ", password="
				+ password + ", date=" + date + "]";
	}
    
}