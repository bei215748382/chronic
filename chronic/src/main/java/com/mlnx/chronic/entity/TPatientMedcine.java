package com.mlnx.chronic.entity;

import java.util.Date;

public class TPatientMedcine {
    private Integer id;

    private Integer patientId; //患者id

    private Integer medcineId;//药物id

    private String dosis;//服药剂量，一次多少

    private String dosingtime;//服药时间，餐前，餐后

    private String times;//服药次数，一日几次

    private Date starttime;//开始服药的时间

    private Integer docId;//医生id

    private Date timestamp;//开药物处方的时间

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

    public Integer getMedcineId() {
        return medcineId;
    }

    public void setMedcineId(Integer medcineId) {
        this.medcineId = medcineId;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public String getDosingtime() {
        return dosingtime;
    }

    public void setDosingtime(String dosingtime) {
        this.dosingtime = dosingtime;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}