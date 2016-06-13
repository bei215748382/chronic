package com.mlnx.chronic.vo.medcine;

import java.util.Calendar;


/**
 * Created by Administrator on 2016/5/9.
 * 药品设置类型
 */
public class MedicineSet {

    public static final String DATA_FORMAT = "HH:mm";

    private String breakfastTime = "07:30";   // 早饭时间
    private String lunchTime = "11:30";   // 午饭时间
    private String dinnerTime = "17:30";  // 晚饭时间
    private String sleepTime = "21:30"; // 就寝时间

    private Calendar[] calendars = new Calendar[4];

    private int remindIntervalMinute = 30;

	public String getBreakfastTime() {
		return breakfastTime;
	}

	public void setBreakfastTime(String breakfastTime) {
		this.breakfastTime = breakfastTime;
	}

	public String getLunchTime() {
		return lunchTime;
	}

	public void setLunchTime(String lunchTime) {
		this.lunchTime = lunchTime;
	}

	public String getDinnerTime() {
		return dinnerTime;
	}

	public void setDinnerTime(String dinnerTime) {
		this.dinnerTime = dinnerTime;
	}

	public String getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(String sleepTime) {
		this.sleepTime = sleepTime;
	}

	public Calendar[] getCalendars() {
		return calendars;
	}

	public void setCalendars(Calendar[] calendars) {
		this.calendars = calendars;
	}

	public int getRemindIntervalMinute() {
		return remindIntervalMinute;
	}

	public void setRemindIntervalMinute(int remindIntervalMinute) {
		this.remindIntervalMinute = remindIntervalMinute;
	}
    
    
}
