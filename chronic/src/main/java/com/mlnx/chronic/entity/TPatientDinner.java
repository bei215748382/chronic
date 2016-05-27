package com.mlnx.chronic.entity;

import java.util.Date;

public class TPatientDinner {
    private Integer id;

    private Integer patientId;

    private Date breakfast;

    private Date lunch;

    private Date supper;

    private Date timestamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Date getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(Date breakfast) {
        this.breakfast = breakfast;
    }

    public Date getLunch() {
        return lunch;
    }

    public void setLunch(Date lunch) {
        this.lunch = lunch;
    }

    public Date getSupper() {
        return supper;
    }

    public void setSupper(Date supper) {
        this.supper = supper;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}