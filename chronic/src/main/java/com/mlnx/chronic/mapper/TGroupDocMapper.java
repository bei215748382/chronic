package com.mlnx.chronic.mapper;

import com.mlnx.chronic.entity.TGroupDoc;
import java.util.List;

public interface TGroupDocMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TGroupDoc record);

    TGroupDoc selectByPrimaryKey(Integer id);

    List<TGroupDoc> selectAll();

    int updateByPrimaryKey(TGroupDoc record);
}