package com.mlnx.chronic.mapper;

import com.mlnx.chronic.entity.TUserBook;
import java.util.List;

public interface TUserBookMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUserBook record);

    TUserBook selectByPrimaryKey(Integer id);

    List<TUserBook> selectAll();

    int updateByPrimaryKey(TUserBook record);
}