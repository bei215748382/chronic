package com.mlnx.chronic.mapper;

import java.util.List;

import com.mlnx.chronic.entity.TBloodPressureCollection;

public interface TBloodPressureCollectionMapper {

	int deleteByPrimaryKey(Integer id);

    int insert(TBloodPressureCollection record);

    TBloodPressureCollection selectByPrimaryKey(Integer id);

    List<TBloodPressureCollection> selectAll();

    int updateByPrimaryKey(TBloodPressureCollection record);
}