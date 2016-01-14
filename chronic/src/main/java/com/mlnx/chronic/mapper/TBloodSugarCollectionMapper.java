package com.mlnx.chronic.mapper;

import java.util.List;

import com.mlnx.chronic.entity.TBloodSugarCollection;

public interface TBloodSugarCollectionMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(TBloodSugarCollection record);

    TBloodSugarCollection selectByPrimaryKey(Integer id);

    List<TBloodSugarCollection> selectAll();

    int updateByPrimaryKey(TBloodSugarCollection record);
}