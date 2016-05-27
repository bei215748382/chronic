package com.mlnx.chronic.controller;

import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.mlnx.chronic.mapper.TestBase;
import com.mlnx.chronic.repo.BloodSugarRepository;
import com.mlnx.springmvc.entity.Patient;
import com.mlnx.springmvc.entity.R;
import com.mlnx.springmvc.entity.Result;
import com.mlnx.springmvc.util.HttpUtil;

public class MeasureColTest extends TestBase {

	@Autowired
	private BloodSugarRepository bloodSugarRepository;

	@Test
	public void testSearchLastBloodSugar() {
		System.out.println(bloodSugarRepository
				.findLastByPatientIdAndStateAndDate(82121, new Date(), 3));
	}

	@Test
	public void testGetMedicine() {
		String host = "http://www.hyey.com/";
		String path = "database/drugsearch.asp?searchtype=1&";
		Map<String, String> cr = new HashMap<String, String>();
		cr.put("textfield001", "桂龙咳喘宁片");
		String url = host + path + "textfield001="
				+ URLEncoder.encode("桂龙咳喘宁片");
		try {
			String result = HttpUtil.doGet(url);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testD() throws Exception {
		String url = "http://localhost:8080/chronic/measure/syn/bloodPressure/10/10/10/10";
		String result = HttpUtil.doPost(url,null);
		System.out.println(result);
	}
	
	@Test
	public void testE() throws Exception {
		String url = "http://123.57.143.76:8010/api/patientmls/List?access_token=0MJ4BKL7536Ncf3e8a28-1857-4c64-9e45-cb9ed5c791cbX2T6WE481S2A6Z7BC58RIE0HD63TQW5EE900OG367";
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("page", "1");
		parameterMap.put("rows", "10");
		parameterMap.put("q", null);
		String result = HttpUtil.doPost(url,parameterMap);
	//	String result = "{\"status\":100,\"results\":{\"total\":7,\"rows\":[{\"userid\":\"f2c6b04a-df14-4f1d-a118-49f84267eaca\",\"telephone\":\"13563397602\",\"realName\":\"张延文\",\"gender\":true}],\"pageSize\":1}}";
		Result object = JSON.parseObject(result,Result.class);
		List<Patient> list = object.getResults().getRows();
		System.out.println(list.toString());
	}
	
	@Test
	public void testF() throws Exception {
		String res = "{\"a\":\"abasd\",\"b\":\"aasf\"}";
		R o = JSON.parseObject(res, R.class);
		System.out.println(o);
	}
	

	@Test
	public void testG() throws Exception {
		String url = "http://localhost:8082/chronic/measure/syn/bloodPressure/";
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("startTime", 1463817379090l+"");
		parameterMap.put("endTime", 1464076579090l+"");
		parameterMap.put("timestamp", 1463990163915l+"");
		parameterMap.put("id", 51679+"");
		String result = HttpUtil.doPost(url,parameterMap);
		System.out.println(result);
	}
	
	@Test
	public void testH() throws Exception {
		String url = "http://192.168.1.166:8082/chronic/measure/search/bloodSugar/1463710005639/1464142005639/84124/";
		String result = HttpUtil.doPost(url,null);
		System.out.println(result);
	}
	
	@Test
	public void getBloodSugarMonth() throws Exception{
		String url = "http://192.168.1.166:8082/chronic/measure/search/bloodSugar/month";
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("patientId", 84124+"");
		String result = HttpUtil.doPost(url,parameterMap);
		System.out.println(result);
	}
	@Test
	public void getBloodSugarDate() throws Exception{
		String url = "http://192.168.1.166:8082/chronic/measure/search/bloodSugar/date";
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("patientId", 84124+"");
		parameterMap.put("date", "2016-05");
		String result = HttpUtil.doPost(url,parameterMap);
		System.out.println(result);
	}
	@Test
	public void getBloodPressureMonth() throws Exception{
		String url = "http://192.168.1.166:8082/chronic/measure/get/bloodPressure/month";
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("patientId", 84124+"");
		String result = HttpUtil.doPost(url,parameterMap);
		System.out.println(result);
	}
	@Test
	public void getBloodPressureDate() throws Exception{
		String url = "http://192.168.1.166:8082/chronic/measure/get/bloodPressure/date";
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("patientId", 84124+"");
		parameterMap.put("date", "2016-05");
		String result = HttpUtil.doPost(url,parameterMap);
		System.out.println(result);
	}
}
