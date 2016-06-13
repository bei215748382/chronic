package com.mlnx.chronic.vo.medcine;

/**
 * Created by Administrator on 2016/5/9.
 * 处方药品
 */
public class MedicineP extends Medicine {

    private boolean isRemind;   // 需要提醒
    private String dosage;    // 一次1片
    private EatStatus[] eatStatuses;  // 吃药状态
    private int totalCount; // 总共吃的次数
    private int alreadyEatCount;    // 已经吃的次数

    public MedicineP() {
        dosage = "一次1片";
        eatStatuses = new EatStatus[3];
        eatStatuses[0] = EatStatus.BEFORE_BREAK_FAST;
        eatStatuses[1] = EatStatus.BEFORE_LUNCH;
        eatStatuses[2] = EatStatus.BEFORE_DINNER;

        totalCount = 15;
    }

    public boolean eatStatusExist(EatStatus eatStatus) {
        for (int i = 0; i < eatStatuses.length; ++i) {
            if (eatStatuses[i].compareTo(eatStatus) == 0)
                return true;
        }
        return false;
    }

    public EatStatus[] getEatStatus() {
        return eatStatuses;
    }

    public void setEatStatuses(EatStatus[] eatStatuses) {
        this.eatStatuses = eatStatuses;
    }

    public int getCountPreDay() {
        return eatStatuses.length;
    }


    public int getAlreadyEatCount() {
        return alreadyEatCount;
    }

    public void setAlreadyEatCount(int alreadyEatCount) {
        this.alreadyEatCount = alreadyEatCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isRemind() {
        return isRemind;
    }

    public void setIsRemind(boolean isRemind) {
        this.isRemind = isRemind;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }


    public boolean finishEat() {
        return alreadyEatCount >= totalCount;
    }


}
