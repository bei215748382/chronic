package com.mlnx.chronic.service;


import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mlnx.chronic.entity.TMedcine;
import com.mlnx.chronic.entity.TPatientMedcine;
import com.mlnx.chronic.mapper.TestBase;
import com.mlnx.chronic.service.MedcineService;
import com.mlnx.chronic.util.StringUtil;
import com.mlnx.chronic.vo.Prescription;

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

	@Test
	public void testGetMedcine(){
		TPatientMedcine tpm = new TPatientMedcine();
		tpm.setPatientId(15);
		tpm.setDosingtime("饭后");
		Map<String,Object> map  = medcineService.getMedcine(tpm);
		List<Prescription> list = (List<Prescription>)map.get(StringUtil.responseObjList);
		for(Prescription p : list){
			System.out.println(p.getDosingtime());
			System.out.println(p.getMedcine().getName());
		}
	}
}
