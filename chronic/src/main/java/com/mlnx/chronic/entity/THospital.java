package com.mlnx.chronic.entity;

public class THospital {
    private Integer id;

    private Integer cityId;

    private String name;
    
    private Integer levelId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public Integer getLevelId() {
		return levelId;
	}

	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}

	@Override
	public String toString() {
		return "THospital [id=" + id + ", cityId=" + cityId + ", name=" + name
				+ ", levelId=" + levelId + "]";
	}
    
}