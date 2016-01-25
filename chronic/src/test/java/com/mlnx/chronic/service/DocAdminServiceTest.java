package com.mlnx.chronic.service;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mlnx.chronic.entity.TUser;
import com.mlnx.chronic.entity.TUserDoc;
import com.mlnx.chronic.mapper.TestBase;
public class DocAdminServiceTest extends TestBase{
	
	@Autowired
	private DocAdminService docService;

	@Test
	public void testRegist() throws Exception {
		long phone =12312341000l;
		for(int i = 0 ;i<10 ;i++){
			TUser user = new TUser();
			TUserDoc doc = new TUserDoc();
			user.setPhone(phone+i+"");
			user.setPassword("123456");
			doc.setName("姓名"+i);
			doc.setSex("男");
			doc.setBirthday(new Date());
			doc.setOffice("科室");
			doc.setTitle("职称");
			doc.setAddressId(1);
			docService.regist(doc, user);
		}
	}

	@Test
	public void testFindAll() {
		System.out.println(docService.findAll());
	}

	@Test
	public void testFindById() {
		System.out.println(docService.findById(1));
	}

	@Test
	public void findAllService(){
		System.out.println(docService.findAllService());
	}
	
	@Test 
	public void testFindDeviceById(){
		System.out.println(docService.findDeviceById(4));
	}
}
