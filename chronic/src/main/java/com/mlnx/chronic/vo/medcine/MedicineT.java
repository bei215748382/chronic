package com.mlnx.chronic.vo.medcine;

import java.util.Date;

/**
 * Created by Administrator on 2016/5/9.
 * 服药提醒的药物对象
 */
public class MedicineT extends Medicine {

    private String dosage;    // 一次1片
    private EatStatus[] eatStatus;
    private Date medicineEatTime;  // 实际每种药的服药时间

    private boolean eat;  // 已经吃啦

    public MedicineT() {
        dosage = "一次1片";
    }

    public MedicineT(MedicineP medicineP) {
        super(medicineP);
        dosage = medicineP.getDosage();
        eatStatus = medicineP.getEatStatus();
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public Date getMedicineEatTime() {
        return medicineEatTime;
    }

    public void setMedicineEatTime(Date medicineEatTime) {
        this.medicineEatTime = medicineEatTime;
    }

    public boolean isEat() {
        return eat;
    }

    public void setEat(boolean eat) {
        this.eat = eat;
    }

    public EatStatus[] getEatStatus() {
        return eatStatus;
    }

    public void setEatStatus(EatStatus[] eatStatus) {
        this.eatStatus = eatStatus;
    }
}
