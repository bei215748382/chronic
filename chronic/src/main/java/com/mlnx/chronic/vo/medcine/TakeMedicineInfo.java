package com.mlnx.chronic.vo.medcine;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/5/9.
 * 服药对象
 */
public class TakeMedicineInfo {

    private Integer id;
    private Integer patientID;  // 病人用户ID

    private List<MedicineT> medicineTs; // 需要服药对象

    private String feedback;    // 用药反馈
    private EatStatus eatStatus;// 状态
    private Date remindTime;  // 服药提醒时间 这个唯一

    public EatStatus getEatStatus() {
        return eatStatus;
    }

    public void setEatStatus(EatStatus eatStatus) {
        this.eatStatus = eatStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPatientID() {
        return patientID;
    }

    public void setPatientID(Integer patientID) {
        this.patientID = patientID;
    }

    public List<MedicineT> getMedicineTs() {
        return medicineTs;
    }

    public void setMedicineTs(List<MedicineT> medicineTs) {
        this.medicineTs = medicineTs;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Date getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(Date remindTime) {
        this.remindTime = remindTime;
    }

    @Override
    public String toString() {
        return "TakeMedicineInfo{" +
                "id=" + id +
                ", patientID=" + patientID +
                ", medicineTs=" + medicineTs +
                ", feedback='" + feedback + '\'' +
                ", remindTime=" + remindTime.getTime() +
                '}';
    }
}
