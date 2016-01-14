package com.mlnx.chronic.mapper;

import java.util.List;

import com.mlnx.chronic.entity.TRemind;

public interface TRemindMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TRemind record);

    TRemind selectByPrimaryKey(Integer id);

    List<TRemind> selectAll();

    int updateByPrimaryKey(TRemind record);
}