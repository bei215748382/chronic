package com.mlnx.chronic.entity;

public class TGroup {
    private Integer id;

    private String name;

    private Integer userId;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

	@Override
	public String toString() {
		return "TGroup [id=" + id + ", name=" + name + ", userId=" + userId
				+ "]";
	}
    
}