package com.mlnx.chronic.mapper;

import java.util.List;

import com.mlnx.chronic.entity.TVisitTest;

public interface TVisitTestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TVisitTest record);

    TVisitTest selectByPrimaryKey(Integer id);

    List<TVisitTest> selectAll();

    int updateByPrimaryKey(TVisitTest record);
}