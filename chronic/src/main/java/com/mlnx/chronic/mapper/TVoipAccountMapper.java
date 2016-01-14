package com.mlnx.chronic.mapper;

import java.util.List;

import com.mlnx.chronic.entity.TVoipAccount;
import com.mlnx.chronic.vo.UsrVoipInfo;

public interface TVoipAccountMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(TVoipAccount record);

	TVoipAccount selectByPrimaryKey(Integer id);

	List<TVoipAccount> selectAll();

	int updateByPrimaryKey(TVoipAccount record);

	/**
	 * 获取voip账号
	 * 
	 * @param list
	 * @return
	 */
	List<UsrVoipInfo> findVoipAccountList(List<Integer> list);
}