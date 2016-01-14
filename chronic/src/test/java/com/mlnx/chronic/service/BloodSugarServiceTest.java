package com.mlnx.chronic.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mlnx.chronic.mapper.TestBase;
import com.mlnx.chronic.service.BloodSugarService;

public class BloodSugarServiceTest extends TestBase {
	
	@Autowired
	private BloodSugarService bloodSugarService;

	@Test
	public void testFindByPatientIdLimitOne() {
		System.out.println(bloodSugarService.findByPatientIdLimitOne(82121));
	}

	@Test
	public void testFindCountByPatientId() {
		System.out.println(bloodSugarService.findCountByPatientId(82121));
	}

}
