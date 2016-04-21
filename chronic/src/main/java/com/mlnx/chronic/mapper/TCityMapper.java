package com.mlnx.chronic.mapper;

import com.mlnx.chronic.entity.TCity;

import java.util.List;

public interface TCityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TCity record);

    TCity selectByPrimaryKey(Integer id);

    List<TCity> selectAll();

    int updateByPrimaryKey(TCity record);

	List<TCity> findByProvinceId(Integer id);

	List<TCity> findByProvinceName(String id);

}