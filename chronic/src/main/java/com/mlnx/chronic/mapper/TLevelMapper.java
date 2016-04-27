package com.mlnx.chronic.mapper;

import com.mlnx.chronic.entity.TLevel;
import java.util.List;

public interface TLevelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TLevel record);

    TLevel selectByPrimaryKey(Integer id);

    List<TLevel> selectAll();

    int updateByPrimaryKey(TLevel record);
}