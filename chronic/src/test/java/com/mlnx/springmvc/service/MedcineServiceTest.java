package com.mlnx.springmvc.service;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mlnx.chronic.entity.TMedcine;
import com.mlnx.chronic.mapper.TestBase;

public class MedcineServiceTest extends TestBase{
	
	@Autowired
	private MedcineService medcineService;

	@Test
	public void testRegist() {
		TMedcine medcine = new TMedcine();
		medcine.setName("测试药物");
		medcine.setPic("无");
		medcine.setDescription("测试用的药物");
		medcine.setType("测试");
		System.out.println(medcineService.regist(medcine));
	}

	@Test
	public void testDelete() {
		System.out.println(medcineService.delete(1));
	}

	@Test
	public void testUpdate() {
		TMedcine medcine = new TMedcine();
		medcine.setId(2);
		medcine.setPic("asdfasdf");
		System.out.println(medcineService.update(medcine));
	}

	@Test
	public void testFindAll() {
		System.out.println(medcineService.findAll());
	}

}
