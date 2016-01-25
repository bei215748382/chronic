package com.mlnx.chronic.vo;

import java.util.Date;
import java.util.List;

import com.mlnx.chronic.entity.TDevice;
import com.mlnx.chronic.entity.TUserDoc;
import com.mlnx.chronic.entity.TUserExt;

public class ServiceVo {
	
	private Integer id;

	private Date start;

	private Date end;

	private TUserDoc doc;

	private TUserExt patient;

	private Integer type;

	private List<TDevice> device;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public TUserDoc getDoc() {
		return doc;
	}

	public void setDoc(TUserDoc doc) {
		this.doc = doc;
	}

	public TUserExt getPatient() {
		return patient;
	}

	public void setPatient(TUserExt patient) {
		this.patient = patient;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<TDevice> getDevice() {
		return device;
	}

	public void setDevice(List<TDevice> device) {
		this.device = device;
	}

	@Override
	public String toString() {
		return "ServiceVo [id=" + id + ", start=" + start + ", end=" + end
				+ ", doc=" + doc + ", patient=" + patient + ", type=" + type
				+ ", device=" + device + "]";
	}

}
