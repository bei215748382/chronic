package com.mlnx.chronic.entity;

public class TGroupPatient {
    private Integer id;

    private Integer groupId;

    private Integer patientId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

	@Override
	public String toString() {
		return "TGroupPatient [id=" + id + ", groupId=" + groupId
				+ ", patientId=" + patientId + "]";
	}
    
}