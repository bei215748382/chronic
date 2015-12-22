package com.mlnx.chronic.entity;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BloodPressure {

    private Integer patientId;

    private String deivceId;

    private Date dateTime;

    private Integer value_diastolic;

    private Integer value_systolic;

    private Integer heart_rate;

    private String state;

    private String comment;

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getDeivceId() {
        return deivceId;
    }

    public void setDeivceId(String deivceId) {
        this.deivceId = deivceId;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getValue_diastolic() {
        return value_diastolic;
    }

    public void setValue_diastolic(Integer value_diastolic) {
        this.value_diastolic = value_diastolic;
    }

    public Integer getValue_systolic() {
        return value_systolic;
    }

    public void setValue_systolic(Integer value_systolic) {
        this.value_systolic = value_systolic;
    }

    public Integer getHeart_rate() {
        return heart_rate;
    }

    public void setHeart_rate(Integer heart_rate) {
        this.heart_rate = heart_rate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

	@Override
	public String toString() {
		return "BloodPressure [patientId=" + patientId + ", deivceId="
				+ deivceId + ", dateTime=" + dateTime + ", value_diastolic="
				+ value_diastolic + ", value_systolic=" + value_systolic
				+ ", heart_rate=" + heart_rate + ", state=" + state
				+ ", comment=" + comment + "]";
	}

}
