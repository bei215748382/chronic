package com.mlnx.chronic.entity;

public class TCity {
    private Integer id;

    private String name;

    private Integer provinceId;

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

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

	@Override
	public String toString() {
		return "TCity [id=" + id + ", name=" + name + ", provinceId="
				+ provinceId + "]";
	}
    
}