package com.mlnx.chronic.mapper;

import java.util.List;

import com.mlnx.chronic.entity.TRemindType;

public interface TRemindTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TRemindType record);

    TRemindType selectByPrimaryKey(Integer id);

    List<TRemindType> selectAll();

    int updateByPrimaryKey(TRemindType record);
}