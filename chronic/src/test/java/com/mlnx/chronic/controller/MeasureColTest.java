package com.mlnx.chronic.controller;

import static org.junit.Assert.*;

import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mlnx.chronic.mapper.TestBase;
import com.mlnx.chronic.repo.BloodSugarRepository;
import com.mlnx.springmvc.util.HttpUtil;

public class MeasureColTest extends TestBase{

	@Autowired
	private BloodSugarRepository bloodSugarRepository;
	
	@Test
	public void testSearchLastBloodSugar() {
		System.out.println(bloodSugarRepository.findLastByPatientIdAndStateAndDate(82121, new Date(),3));
	}

	 @Test
	public void testGetMedicine(){
	        String host = "http://www.hyey.com/";
	        String path = "database/drugsearch.asp?searchtype=1&";
	        Map<String, String> cr = new HashMap<String,String>();
	        cr.put("textfield001", "桂龙咳喘宁片");
	        String url = host+path+"textfield001="+URLEncoder.encode("桂龙咳喘宁片");
	        try {
	            String result = HttpUtil.doGet(url);
	            System.out.println(result);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
