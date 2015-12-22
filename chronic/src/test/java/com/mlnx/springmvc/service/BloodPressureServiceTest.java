package com.mlnx.springmvc.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mlnx.chronic.mapper.TestBase;

public class BloodPressureServiceTest extends TestBase{
	
	@Autowired
	private BloodPressureService bloodPressureService;

	@Test
	public void testFindByPatientIdLimitOne() {
		System.out.println(bloodPressureService.findByPatientIdLimitOne(82121));
	}

	@Test
	public void testFindCountByPatientId() {
		System.out.println(bloodPressureService.findCountByPatientId(82121));
	}

}
