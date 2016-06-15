package com.mlnx.chronic.service;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mlnx.chronic.entity.TMedicine;
import com.mlnx.chronic.mapper.TestBase;

public class MedicineServiceTest extends TestBase{
	
	@Autowired
	private MedicineService medicineService;

	@Test
	public void testRegist() {
		TMedicine medicine = new TMedicine();
		medicine.setName("测试药物");
		medicine.setPic("无");
		medicine.setDescription("测试用的药物");
		medicine.setType("测试");
		System.out.println(medicineService.regist(medicine));
	}

	@Test
	public void testDelete() {
		System.out.println(medicineService.delete(1));
	}

	@Test
	public void testUpdate() {
		TMedicine medicine = new TMedicine();
		medicine.setId(2);
		medicine.setPic("asdfasdf");
		System.out.println(medicineService.update(medicine));
	}

	@Test
	public void testFindAll() {
		System.out.println(medicineService.findAll());
	}

}
