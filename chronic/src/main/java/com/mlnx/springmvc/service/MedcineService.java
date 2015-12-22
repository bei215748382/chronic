package com.mlnx.springmvc.service;

import java.util.List;

import com.mlnx.chronic.entity.TMedcine;
import com.mlnx.springmvc.util.ChronicResponse;

public interface MedcineService {

	// 添加药物
	public ChronicResponse regist(TMedcine medcine);

	//根据id删除
	public ChronicResponse delete(int id);
	
	//更新药物内容
	public ChronicResponse update(TMedcine medcine);

	//查找所有药物
	public List<TMedcine> findAll();

}
