package com.mlnx.chronic.controller;


import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.mlnx.chronic.mapper.TestBase;
import com.mlnx.chronic.service.InquiryService;
import com.mlnx.springmvc.util.HttpUtil;

public class InquiryColTest extends TestBase{
	
	@Autowired
	private InquiryService inquiryService;

	@Test
	public void testFindAllProvince() {
		System.out.println(inquiryService.findAllProvince());
	}
	

	@Test
	public void testFindAllCity() {
		System.out.println(inquiryService.findAllCity("宁夏"));
	}

	
	@Test
	public void findAllHospitalWithLevel() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "南京市");
		System.out.println(inquiryService.findAllHospitalWithMap(map));
	}
	
    @Test
    public void test() {
        String url = "http://localhost:8080/chronic/inquiry/all/hospital";
        Map<String,String> map = new HashMap<String,String>();
        map.put("id", "6");
        try {
            System.out.println(HttpUtil.doPost(url, map));
            Map<String,Object> cr = JSON.parseObject(HttpUtil.doPost(url, map), Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void findAllDocWithHospitalAndGroup(){
    	Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "江苏省人民医院");
		map.put("group", 1);
		System.out.println(inquiryService.findAllDocWithHospitalAndGroup(map));
    }

    @Test
    public void testB() {
        String url = "http://localhost:8080/chronic/inquiry/all/doc/with/map";
        Map<String,String> map = new HashMap<String,String>();
        map.put("name", "姓名");
        try {
            System.out.println(HttpUtil.doPost(url, map));
            Map<String,Object> cr = JSON.parseObject(HttpUtil.doPost(url, map), Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testC(){
    	String key = "江苏";
    	System.out.println(inquiryService.findAllDocWithKey(key));
    }
    @Test
    public void testD(){
    	  Map<String,Object> map = new HashMap<String,Object>();
          map.put("cityId", "南京市");
          map.put("disease", "心");
    	System.out.println(inquiryService.findAllDocByCity(map));
    }
    
    
}
