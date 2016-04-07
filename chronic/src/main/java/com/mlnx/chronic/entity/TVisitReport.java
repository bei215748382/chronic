package com.mlnx.chronic.entity;

import java.util.Date;

public class TVisitReport {
    private Integer id;

    private Integer visitId;

    private String conditiona;

    private String medicine;

    private String pic;

    private Date timestamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVisitId() {
        return visitId;
    }

    public void setVisitId(Integer visitId) {
        this.visitId = visitId;
    }

    public String getConditiona() {
		return conditiona;
	}

	public void setConditiona(String conditiona) {
		this.conditiona = conditiona;
	}

	public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

	@Override
	public String toString() {
		return "TVisitReport [id=" + id + ", visitId=" + visitId
				+ ", conditiona=" + conditiona + ", medicine=" + medicine
				+ ", pic=" + pic + ", timestamp=" + timestamp + "]";
	}
    
}