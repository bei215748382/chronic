package com.mlnx.chronic.entity;

import java.util.Date;

public class TExercisePrescription {
    private Integer id;

    private Date exercisePrescriptionTime;

    private String diagnosis;

    private Integer exerciseType;

    private Integer patientId;

    private String exercisePeriod;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getExercisePrescriptionTime() {
        return exercisePrescriptionTime;
    }

    public void setExercisePrescriptionTime(Date exercisePrescriptionTime) {
        this.exercisePrescriptionTime = exercisePrescriptionTime;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Integer getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(Integer exerciseType) {
        this.exerciseType = exerciseType;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getExercisePeriod() {
        return exercisePeriod;
    }

    public void setExercisePeriod(String exercisePeriod) {
        this.exercisePeriod = exercisePeriod;
    }
}