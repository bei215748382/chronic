package com.mlnx.chronic.mapper;

import java.util.List;

import com.mlnx.chronic.entity.TMedcine;

public interface TMedcineMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(TMedcine record);

    TMedcine selectByPrimaryKey(Integer id);

    List<TMedcine> selectAll();

    int updateByPrimaryKey(TMedcine record);
}