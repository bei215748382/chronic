package com.mlnx.chronic.mapper;

import com.mlnx.chronic.entity.TAddress;
import java.util.List;

public interface TAddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TAddress record);

    TAddress selectByPrimaryKey(Integer id);

    List<TAddress> selectAll();

    int updateByPrimaryKey(TAddress record);
}