package com.mlnx.chronic.mapper;

import java.util.List;

import com.mlnx.chronic.entity.TReport;

public interface TReportMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(TReport record);

	TReport selectByPrimaryKey(Integer id);

	List<TReport> selectAll();

	int updateByPrimaryKey(TReport record);

	List<TReport> selectByUserId(Integer id);

	/**
	 * 获取用户最近一次报告
	 * 
	 * @param id
	 * @return
	 */
	TReport selectLastByUserId(Integer id);
}