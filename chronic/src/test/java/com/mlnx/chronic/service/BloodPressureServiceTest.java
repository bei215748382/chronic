package com.mlnx.chronic.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mlnx.chronic.entity.TPatientBloodPressure;
import com.mlnx.chronic.mapper.TestBase;

public class BloodPressureServiceTest extends TestBase{
	
	@Autowired
	private BloodPressureService bloodPressureService;
	
	@Test
	public void testFindByPatientIdLimitOne() {
		System.out.println(bloodPressureService.findByPatientIdLimitOne(15));
	}

	@Test
	public void testFindCountByPatientId() {
		System.out.println(bloodPressureService.findCountByPatientId(15));
	}

	@Test
	public void addBloodPressure() throws ParseException {
		TPatientBloodPressure bloodPressure = new TPatientBloodPressure();
		bloodPressure.setPatientId(15);
		bloodPressure.setDiastolic("75");
		bloodPressure.setSystolic("128");
		bloodPressure.setHeartRate(73);
		bloodPressure.setState("静坐");
		bloodPressure.setDateTime(sdf.parse("2016-05-11 11:11:12"));
		System.out.println(bloodPressureService.addBloodPressure(bloodPressure));
	}

	@Test
	public void getBloodPressureWithEndAndLimit() {
		Integer patientId = 15;
		Long endTime = new Date().getTime();
		Integer limit = 1;
		System.out.println(bloodPressureService.getBloodPressureWithEndAndLimit(limit,endTime,patientId));
	}

	@Test
	public void getBloodPressureByPatientIdWithTimeRange() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Integer patientId = 15;
		Long startTime = sdf.parse("2016-05-10").getTime();
		Long endTime =  sdf.parse("2016-05-17").getTime();
		System.out.println(bloodPressureService.getBloodPressureByPatientIdWithTimeRange(startTime,endTime,patientId));
	}

	@Test
	public void synBloodPressureWithTimeRange() throws ParseException{
		Integer patientId = 15;
		Long startTime = sdf.parse("2016-05-10 02:12:12").getTime();
		Long endTime =  sdf.parse("2016-05-17 02:12:12").getTime();
		Long timestamp = sdf.parse("2016-05-13 07:02:02").getTime();
		System.out.println(bloodPressureService.synBloodPressureWithTimeRange(startTime,endTime,patientId,timestamp));
	}
}
