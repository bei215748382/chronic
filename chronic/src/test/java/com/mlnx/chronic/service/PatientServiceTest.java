package com.mlnx.chronic.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mlnx.chronic.entity.TGroupPatient;
import com.mlnx.chronic.entity.TIdentity;
import com.mlnx.chronic.exception.RegisterException;
import com.mlnx.chronic.mapper.TestBase;

public class PatientServiceTest extends TestBase {
	
	@Autowired
	private PatientService patientService;

	@Test
	public void testRegist() throws RegisterException {
		TIdentity identity = new TIdentity();
		identity.setName("330206198902014862");
		identity.setUserId(15);
		System.out.println(patientService.regist(identity));
	}

	@Test
	public void testAddToGroup() {
		TGroupPatient groupPatient = new TGroupPatient();
		groupPatient.setGroupId(3);
		groupPatient.setPatientId(40023);
		System.out.println(patientService.addToGroup(groupPatient));
	}

	@Test
	public void testEdit() {
		TGroupPatient groupPatient = new TGroupPatient();
		groupPatient.setId(1);
		groupPatient.setGroupId(4);
		System.out.println(patientService.edit(groupPatient));
	}

	@Test
	public void testDelete() {
		System.out.println(patientService.delete(1));
	}

	@Test
	public void testFindGroupPatients() {
		System.out.println(patientService.findGroupPatients(3));
	}

}
