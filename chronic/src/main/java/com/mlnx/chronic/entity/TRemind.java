package com.mlnx.chronic.entity;

import java.util.Date;

public class TRemind {
    private Integer id;

    private Integer userId;

    private Integer typeId;

    private Date remindTime;

    private Integer repeat;

    private String remindWeek;

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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Date getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(Date remindTime) {
        this.remindTime = remindTime;
    }

    public Integer getRepeat() {
        return repeat;
    }

    public void setRepeat(Integer repeat) {
        this.repeat = repeat;
    }

    public String getRemindWeek() {
        return remindWeek;
    }

    public void setRemindWeek(String remindWeek) {
        this.remindWeek = remindWeek;
    }
}