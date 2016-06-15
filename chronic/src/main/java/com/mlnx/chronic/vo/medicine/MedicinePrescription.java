package com.mlnx.chronic.vo.medicine;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/5/9.
 */
public class MedicinePrescription {

    private Integer id;
    private Integer patientID;  // 病人用户ID

    private List<MedicineP> medicinePs;   // 处方药品
    private Date startMpTime;    // 处方开始时间
    private Integer doctorID;    // 开处方的医生
    private String doctorName;  // 开处方的医生姓名
    private PrescriptionType prescriptionType;  // 处方类型

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

    public List<MedicineP> getMedicinePs() {
        return medicinePs;
    }

    public void setMedicinePs(List<MedicineP> medicinePs) {
        this.medicinePs = medicinePs;
    }

    public Date getStartMpTime() {
        return startMpTime;
    }

    public void setStartMpTime(Date startMpTime) {
        this.startMpTime = startMpTime;
    }

    public Integer getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(Integer doctorID) {
        this.doctorID = doctorID;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public PrescriptionType getPrescriptionType() {
        return prescriptionType;
    }

    public void setPrescriptionType(PrescriptionType prescriptionType) {
        this.prescriptionType = prescriptionType;
    }

    @Override
    public String toString() {
        return "MedicinePrescription [id=" + id + ", patientID=" + patientID
                + ", medicinePs=" + medicinePs + ", startMpTime=" + startMpTime
                + ", doctorID=" + doctorID + ", doctorName=" + doctorName
                + ", prescriptionType=" + prescriptionType + "]";
    }

}
