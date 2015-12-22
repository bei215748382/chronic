package com.mlnx.chronic.controller;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mlnx.chronic.mapper.TestBase;
import com.mlnx.chronic.repo.BloodSugarRepository;

public class MeasureColTest extends TestBase{

	@Autowired
	private BloodSugarRepository bloodSugarRepository;
	
	@Test
	public void testSearchLastBloodSugar() {
		System.out.println(bloodSugarRepository.findLastByPatientIdAndStateAndDate(82121, new Date(),3));
	}

}
