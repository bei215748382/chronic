package com.mlnx.chronic.entity;

import java.util.Date;

public class TExercise {
    private Integer id;

    private Integer patientId;

    private Integer exerciseTypeId;

    private Date startExerciseTime;

    private Date stopExerciseTime;

    private Float met;

    private Float energy;

    private String summary;

    private Float motionDistance;

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

    public Integer getExerciseTypeId() {
        return exerciseTypeId;
    }

    public void setExerciseTypeId(Integer exerciseTypeId) {
        this.exerciseTypeId = exerciseTypeId;
    }

    public Date getStartExerciseTime() {
        return startExerciseTime;
    }

    public void setStartExerciseTime(Date startExerciseTime) {
        this.startExerciseTime = startExerciseTime;
    }

    public Date getStopExerciseTime() {
        return stopExerciseTime;
    }

    public void setStopExerciseTime(Date stopExerciseTime) {
        this.stopExerciseTime = stopExerciseTime;
    }

    public Float getMet() {
        return met;
    }

    public void setMet(Float met) {
        this.met = met;
    }

    public Float getEnergy() {
        return energy;
    }

    public void setEnergy(Float energy) {
        this.energy = energy;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Float getMotionDistance() {
        return motionDistance;
    }

    public void setMotionDistance(Float motionDistance) {
        this.motionDistance = motionDistance;
    }
}