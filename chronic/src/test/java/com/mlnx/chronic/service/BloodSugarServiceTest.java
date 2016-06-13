package com.mlnx.chronic.service;


import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mlnx.chronic.entity.TPatientBloodSugar;
import com.mlnx.chronic.mapper.TestBase;
import com.mlnx.chronic.service.BloodSugarService;
import com.mlnx.chronic.util.StringUtil;

public class BloodSugarServiceTest extends TestBase {
	
	@Autowired
	private BloodSugarService bloodSugarService;
	
	@Test
	public void testFindByPatientIdLimitOne() {
		System.out.println(bloodSugarService.findByPatientIdLimitOne(15));
	}

	@Test
	public void testFindCountByPatientId() {
		System.out.println(bloodSugarService.findCountByPatientId(15));
	}
	
	@Test
	public void addBloodSugar() throws ParseException {
		Date base = new Date(2015-1900,5-1,31,8,0);
		long oneday = 24*3600*1000;
		for(int i = 0;i<200;i++){
			float value = (float) ((Math.random()-0.5)*10+7);
			Date date = new Date(base.getTime()+oneday*i);
			TPatientBloodSugar bs = new TPatientBloodSugar();
			bs.setPatientId(19);
			bs.setValue(value+"");
			bs.setDateTime(date);
			bloodSugarService.addBloodSugar(bs);
		}
	}
	
	@Test
	public void searchBloodSugarWithTimeRange() throws ParseException{	
		Integer patientId = 15;
		Long startTime = sdf.parse("2016-05-13 02:12:12").getTime();
		Long endTime =  sdf.parse("2016-05-17 02:12:12").getTime();
		System.out.println(bloodSugarService.searchBloodSugarWithTimeRange(startTime,endTime,patientId));
	}
	
	@Test
	public void searchLastBloodSugar(){
		Integer patientId = 15;
		Long endTime = new Date().getTime();
		Integer limit = 1;
		String state = "饭后";
		System.out.println(bloodSugarService.searchLastBloodSugar(limit,endTime,patientId,state));
	}

	@Test
	public void synBloodSugarWithTimeRange() throws ParseException{
		Integer patientId = 15;
		Long startTime = sdf.parse("2016-05-10 02:12:12").getTime();
		Long endTime =  sdf.parse("2016-05-17 02:12:12").getTime();
		Long timestamp = sdf.parse("2016-05-13 17:02:02").getTime();
		System.out.println(bloodSugarService.synBloodSugarWithTimeRange(startTime,endTime,patientId,timestamp));
	}
	
	@Test
	public void getBloodSugarMonth(){
		Map<String,Object> map = bloodSugarService.getBloodSugarMonth(15);
		List<Date> list = (List<Date>) map.get(StringUtil.responseObjList);
		for(Date l : list){
			System.out.println(l.toLocaleString());
		}
	}
}
