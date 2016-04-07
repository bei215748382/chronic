package com.mlnx.chronic.websocket;

import java.util.Date;
import java.util.List;

import com.mlnx.chronic.ecg.EcgLead;



public class EcgResponse {

    private Integer patientId;

    private List<EcgLead> leadList;

    private Long token;

    private Date startDateTime;

    private Date endDateTime;

    private List<float[]> dataPoints;
    
    private List<Double> x;

    private List<Integer> sampling_rates;

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public List<EcgLead> getLeadList() {
        return leadList;
    }

    public void setLeadList(List<EcgLead> leadList) {
        this.leadList = leadList;
    }

    public Long getToken() {
        return token;
    }

    public void setToken(Long token) {
        this.token = token;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public List<float[]> getDataPoints() {
        return dataPoints;
    }

    public void setDataPoints(List<float[]> dataPoints) {
        this.dataPoints = dataPoints;
    }

    public List<Integer> getSampling_rates() {
        return sampling_rates;
    }

    public void setSampling_rates(List<Integer> sampling_rates) {
        this.sampling_rates = sampling_rates;
    }

    public List<Double> getX() {
        return x;
    }

    public void setX(List<Double> x) {
        this.x = x;
    }

}
