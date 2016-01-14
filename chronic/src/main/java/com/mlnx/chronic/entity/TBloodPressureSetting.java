package com.mlnx.chronic.entity;

public class TBloodPressureSetting {
    private Integer id;

    private Integer high;

    private Integer low;

    private Integer periodic;

    private Integer userId;

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

    public Integer getPeriodic() {
        return periodic;
    }

    public void setPeriodic(Integer periodic) {
        this.periodic = periodic;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

	@Override
	public String toString() {
		return "TBloodPressureSetting [id=" + id + ", high=" + high + ", low="
				+ low + ", periodic=" + periodic + ", userId=" + userId + "]";
	}
}