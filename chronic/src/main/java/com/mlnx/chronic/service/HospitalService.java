package com.mlnx.chronic.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mlnx.chronic.entity.THospital;

public interface HospitalService {

	/**
	 * 列表
	 */
	Page<THospital> list(Pageable pageable);

	/**
	 * 保存
	 */
	void save(THospital patient);

	/**
	 * 查询
	 */
	THospital get(Integer id);

	/**
	 * 删除
	 */
	void delete(Integer id);

	/**
	 * 查找所有
	 * 
	 * @return
	 */
	List<THospital> findAll();

	/**
	 * 根据医院名称模糊查找
	 * 
	 * @param name
	 * @return
	 */
	List<THospital> findByName(String name);

	/**
	 * 根据城市id查找
	 * 
	 * @param id
	 * @return
	 */
	List<THospital> findByCityId(Integer id);

	/**
	 * 更新数据
	 * 
	 * @param hospital
	 */
	void update(THospital hospital);

	/**
	 * 根据医院的唯一名字查找医院
	 * 
	 * @param hospitalName
	 * @return
	 */
	THospital findByUName(String hospitalName);

}
