package com.mlnx.chronic.mapper;

import java.util.List;

import com.mlnx.chronic.entity.TGroup;

public interface TGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TGroup record);

    TGroup selectByPrimaryKey(Integer id);

    List<TGroup> selectAll();

    int updateByPrimaryKey(TGroup record);

	List<TGroup> searchGroup(Integer id);
}