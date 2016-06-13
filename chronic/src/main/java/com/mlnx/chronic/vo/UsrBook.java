package com.mlnx.chronic.vo;

import java.util.Date;
import java.util.List;

/**
 * Created by shasha on 2016/6/6.
 */
public class UsrBook {

    private int usrId;
    private int doctorId;
    private String describe;//病情描述
    private List<String> pics;//图片
    private Date date;//预约日期
    private BookStatus bookStatuses;//预约具体时间,上午，下午，晚上

    public int getUsrId() {
        return usrId;
    }

    public void setUsrId(int usrId) {
        this.usrId = usrId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BookStatus getBookStatuses() {
        return bookStatuses;
    }

    public void setBookStatuses(BookStatus bookStatuses) {
        this.bookStatuses = bookStatuses;
    }
}
