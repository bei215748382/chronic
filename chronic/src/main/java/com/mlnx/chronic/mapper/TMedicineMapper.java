package com.mlnx.chronic.mapper;

import java.util.List;

import com.mlnx.chronic.entity.TMedicine;

public interface TMedicineMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(TMedicine record);

    TMedicine selectByPrimaryKey(Integer id);

    List<TMedicine> selectAll();

    int updateByPrimaryKey(TMedicine record);
}