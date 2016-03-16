package com.mlnx.chronic.mapper2;

import com.mlnx.chronic.entity2.TApp;

import java.util.List;

public interface TAppMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(TApp record);

	TApp selectByPrimaryKey(Integer id);

	List<TApp> selectAll();

	int updateByPrimaryKey(TApp record);

	TApp searchLastVersionByName(String name);

	/**
	 * 根据包名和版本号查找app
	 * 
	 * @param app
	 * @return
	 */
	TApp selectByName(TApp app);
}