package com.mlnx.chronic.entity;

public class TExerciseType {
    private Integer id;

    private Float met;

    private String exerciseName;

    private String picUrl;

    private String recordDistance;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getMet() {
        return met;
    }

    public void setMet(Float met) {
        this.met = met;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getRecordDistance() {
        return recordDistance;
    }

    public void setRecordDistance(String recordDistance) {
        this.recordDistance = recordDistance;
    }
}