package com.mlnx.chronic.entity;

import java.nio.ByteBuffer;
import java.util.Date;

public class Ecg {

    private Integer patientId;

    private String deivceId;

    private Date dateTime;

    private Integer numChannels;

    private Integer samplingRate;

    private Integer amplification;

    private Integer heartRate;

    private Integer diastolic;

    private Integer systolic;

    private Integer spo2;

    private Integer pose;

    private ByteBuffer data;

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

    public Integer getNumChannels() {

        return numChannels;
    }

    public void setNumChannels(Integer numChannels) {

        this.numChannels = numChannels;
    }

    public Integer getSamplingRate() {

        return samplingRate;
    }

    public void setSamplingRate(Integer samplingRate) {

        this.samplingRate = samplingRate;
    }

    public Integer getAmplification() {

        return amplification;
    }

    public void setAmplification(Integer amplification) {

        this.amplification = amplification;
    }

    public Integer getHeartRate() {

        return heartRate;
    }

    public void setHeartRate(Integer heartRate) {

        this.heartRate = heartRate;
    }

    public Integer getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(Integer diastolic) {
        this.diastolic = diastolic;
    }

    public Integer getSystolic() {
        return systolic;
    }

    public void setSystolic(Integer systolic) {
        this.systolic = systolic;
    }

    public Integer getSpo2() {
        return spo2;
    }

    public void setSpo2(Integer spo2) {
        this.spo2 = spo2;
    }

    public Integer getPose() {

        return pose;
    }

    public void setPose(Integer pose) {

        this.pose = pose;
    }

    public ByteBuffer getData() {

        return data;
    }

    public void setData(ByteBuffer data) {

        this.data = data;
    }
}
