package com.mlnx.chronic.mapper;

import java.util.List;

import com.mlnx.chronic.entity.TReportContent;

public interface TReportContentMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(TReportContent record);

	TReportContent selectByPrimaryKey(Integer id);

	List<TReportContent> selectAll();

	int updateByPrimaryKey(TReportContent record);
}