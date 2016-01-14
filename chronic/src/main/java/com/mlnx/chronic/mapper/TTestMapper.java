package com.mlnx.chronic.mapper;

import java.util.List;

import com.mlnx.chronic.entity.TTest;

public interface TTestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TTest record);

    TTest selectByPrimaryKey(Integer id);

    List<TTest> selectAll();

    int updateByPrimaryKey(TTest record);
}