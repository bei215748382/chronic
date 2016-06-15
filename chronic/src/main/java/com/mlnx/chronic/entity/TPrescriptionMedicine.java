package com.mlnx.chronic.entity;

public class TPrescriptionMedicine {
    private Integer id;

    private Integer prescriptionId;

    private Integer medicineId;

    private String sum;

    private String already;

    private Integer isremind;

    private String dosage;

    private String eatstatuses;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Integer prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public Integer getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Integer medicineId) {
        this.medicineId = medicineId;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getAlready() {
        return already;
    }

    public void setAlready(String already) {
        this.already = already;
    }

    public Integer getIsremind() {
        return isremind;
    }

    public void setIsremind(Integer isremind) {
        this.isremind = isremind;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getEatstatuses() {
        return eatstatuses;
    }

    public void setEatstatuses(String eatstatuses) {
        this.eatstatuses = eatstatuses;
    }
}