package com.mlnx.chronic.vo;

import java.util.Date;
import java.util.List;

/**
 * Created by shasha on 2016/6/6.
 */
public class Book {

    private int doctorId;//医生id
    private Date date;//预约日期
    private List<BookCount> listMap;

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<BookCount> getListMap() {
        return listMap;
    }

    public void setListMap(List<BookCount> listMap) {
        this.listMap = listMap;
    }
}
