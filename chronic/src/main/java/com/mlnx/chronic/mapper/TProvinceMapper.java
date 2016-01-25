package com.mlnx.chronic.mapper;

import com.mlnx.chronic.entity.TProvince;
import java.util.List;

public interface TProvinceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TProvince record);

    TProvince selectByPrimaryKey(Integer id);

    List<TProvince> selectAll();

    int updateByPrimaryKey(TProvince record);
}