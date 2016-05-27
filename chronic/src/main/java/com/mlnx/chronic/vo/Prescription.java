package com.mlnx.chronic.vo;

import com.mlnx.chronic.entity.TMedcine;
import com.mlnx.chronic.entity.TPatientMedcine;

public class Prescription extends TPatientMedcine{
	
	private TMedcine medcine;

	public TMedcine getMedcine() {
		return medcine;
	}

	public void setMedcine(TMedcine medcine) {
		this.medcine = medcine;
	}
	
}
