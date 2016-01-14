package com.mlnx.chronic.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mlnx.chronic.entity.TGroup;
import com.mlnx.chronic.mapper.TestBase;
import com.mlnx.chronic.service.GroupService;

public class GroupServiceTest extends TestBase {

	@Autowired
	private GroupService groupService;

	@Test
	public void testRegist() {
		for (int i = 0; i < 10; i++) {
			TGroup group = new TGroup();
			group.setName("测试组" + i);
			group.setUserId(15);
			System.out.println(groupService.regist(group));
		}
	}

	@Test
	public void testEdit() {
		TGroup group = new TGroup();
		group.setId(1);
		group.setName("测试组编辑");
		System.out.println(groupService.edit(group));
	}

	@Test
	public void testDelete() {
		System.out.println(groupService.delete(2));
	}

}
