package com.mlnx.chronic.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mlnx.chronic.entity.TProvince;

public interface ProvinceService {

	/**
	 * 列表 
	 */
	Page<TProvince> list(Pageable pageable);
	
	/**
	 * 保存
	 */
	void save(TProvince province);

	/**
	 * 查询
	 */
	TProvince get(Integer id);

	/**
	 * 删除
	 */
	void delete(Integer id);
	
	/**
	 * 查找所有
	 * @return
	 */
	List<TProvince> findAll();
	
	/**
	 * 根据城市名模糊查找
	 * @param name
	 * @return
	 */
	List<TProvince> findByName(String name);

	/**
	 * 更新数据
	 * @param province
	 */
	void update(TProvince province);

	/**
	 * 根据唯一命名查找城市
	 * @param name
	 * @return
	 */
	TProvince findByUName(String name);

	/**
	 * 查找所有省
	 * @return
	 */
	Map<String, Object> findAllProvince();
}
