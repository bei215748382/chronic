package com.mlnx.chronic.entity;

import java.util.Date;

public class TPhoneValid {
    private Integer id;

    private String phone;

    private Integer validcode;

    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getValidcode() {
        return validcode;
    }

    public void setValidcode(Integer validcode) {
        this.validcode = validcode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}