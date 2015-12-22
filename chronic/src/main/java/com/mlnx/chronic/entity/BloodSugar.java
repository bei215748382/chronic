package com.mlnx.chronic.entity;

import java.util.Date;

public class BloodSugar {
    private int patient_id;// 病人id
    private String sugar_value;
    private Date date; // 测量时间
    private String state;// 饭前饭后、睡觉前，测试时候的状态
    private boolean medicine;// 是否服药
    private boolean insulin;// 是否注射胰岛素
    private String other_medicine;// 选择口服药物和注射胰岛素
    private int carbohybrate;// 碳水化合物的量
    private String exercise_time;// 运动时间
    private String remark;// 备注

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public String getSugar_value() {
        return sugar_value;
    }

    public void setSugar_value(String sugar_value) {
        this.sugar_value = sugar_value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isMedicine() {
        return medicine;
    }

    public void setMedicine(boolean medicine) {
        this.medicine = medicine;
    }

    public boolean isInsulin() {
        return insulin;
    }

    public void setInsulin(boolean insulin) {
        this.insulin = insulin;
    }

    public String getOther_medicine() {
        return other_medicine;
    }

    public void setOther_medicine(String other_medicine) {
        this.other_medicine = other_medicine;
    }

    public int getCarbohybrate() {
        return carbohybrate;
    }

    public void setCarbohybrate(int carbohybrate) {
        this.carbohybrate = carbohybrate;
    }

    public String getExercise_time() {
        return exercise_time;
    }

    public void setExercise_time(String exercise_time) {
        this.exercise_time = exercise_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	@Override
	public String toString() {
		return "BloodSugar [patient_id=" + patient_id + ", sugar_value="
				+ sugar_value + ", date=" + date + ", state=" + state
				+ ", medicine=" + medicine + ", insulin=" + insulin
				+ ", other_medicine=" + other_medicine + ", carbohybrate="
				+ carbohybrate + ", exercise_time=" + exercise_time
				+ ", remark=" + remark + "]";
	}

}
