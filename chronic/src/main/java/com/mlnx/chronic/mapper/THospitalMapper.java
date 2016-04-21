package com.mlnx.chronic.mapper;

import java.util.List;
import java.util.Map;

import com.mlnx.chronic.entity.THospital;

public interface THospitalMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(THospital record);

    THospital selectByPrimaryKey(Integer id);

    List<THospital> selectAll();

    int updateByPrimaryKey(THospital record);

	List<THospital> findByCityId(Integer id);

	List<THospital> findByCityName(String id);

	List<THospital> findByCityNameWithLevel(Map<String, Object> paramMap);

	List<THospital> findByCityIdWithLevel(Map<String, Object> paramMap);
}