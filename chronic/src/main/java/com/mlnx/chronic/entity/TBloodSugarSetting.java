package com.mlnx.chronic.entity;

public class TBloodSugarSetting {
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
}