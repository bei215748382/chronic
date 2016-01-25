package com.mlnx.chronic.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mlnx.chronic.entity.TCity;

public interface CityService {

	/**
	 * 列表
	 */
	Page<TCity> list(Pageable pageable);

	/**
	 * 保存
	 */
	void save(TCity patient);

	/**
	 * 查询
	 */
	TCity get(Integer id);

	/**
	 * 删除
	 */
	void delete(Integer id);

	/**
	 * 查找所有
	 * 
	 * @return
	 */
	List<TCity> findAll();

	/**
	 * 根据名称模糊查找城市
	 * 
	 * @param name
	 * @return
	 */
	List<TCity> findByName(String name);

	/**
	 * 根据省id查找城市
	 * 
	 * @param id
	 * @return
	 */
	List<TCity> findByProvinceId(Integer id);

	/**
	 * 更新数据
	 * @param city
	 */
	void update(TCity city);

	/**
	 * 根据唯一城市名查找城市
	 * @param cityName
	 * @return
	 */
	TCity findByUName(String cityName);

}
