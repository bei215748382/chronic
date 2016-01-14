package com.mlnx.chronic.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mlnx.chronic.entity.TVisit;
import com.mlnx.chronic.exception.TransactionalException;
import com.mlnx.chronic.mapper.TestBase;
import com.mlnx.chronic.service.VisitService;

public class VisitServiceTest extends TestBase {

	@Autowired
	private VisitService visitService;

	@Test
	public void testRegist() throws TransactionalException {
		TVisit visit = new TVisit();
		visit.setDoctorId(5);
		visit.setPatientId(7);
		visit.setDate(new Date());
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(1);
		ids.add(2);
		System.out.println(visitService.regist(visit, ids));
	}

	@Test
	public void testEdit() {
		TVisit visit = new TVisit();
		visit.setId(1);
		visit.setDoctorId(3);
		System.out.println(visitService.edit(visit));
	}

	@Test
	public void testDelete() {
		System.out.println(visitService.delete(1));
	}

	@Test
	public void testFindAllByDoctorId() {
		System.out.println(visitService.findAllByDoctorId(1));
	}

	@Test
	public void search() {
		System.out.println(visitService.search(5));
	}
}
