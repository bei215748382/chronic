package com.mlnx.chronic.entity;

import java.util.Date;

public class TBloodPressureCollection {
    private Integer id;

    private Integer high;

    private Integer low;

    private Integer normal;

    private Integer total;

    private Date time;

    private Integer userId;

	public TBloodPressureCollection() {

	}

    public TBloodPressureCollection(int high, int low, int normal,
			int total, Integer userId) {
		this.high = high;
		this.low = low;
		this.normal=normal;
		this.total =total;
		this.userId=userId;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHigh() {
        return high;
    }

    public void setHigh(Integer high) {
        this.high = high;
    }

    public Integer getLow() {
        return low;
    }

    public void setLow(Integer low) {
        this.low = low;
    }

    public Integer getNormal() {
        return normal;
    }

    public void setNormal(Integer normal) {
        this.normal = normal;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}