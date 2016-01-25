package com.mlnx.chronic.mapper;

import com.mlnx.chronic.entity.THospital;

import java.util.List;

public interface THospitalMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(THospital record);

    THospital selectByPrimaryKey(Integer id);

    List<THospital> selectAll();

    int updateByPrimaryKey(THospital record);

	List<THospital> findByCityId(Integer id);
}