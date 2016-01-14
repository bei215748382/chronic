package com.mlnx.chronic.mapper;

import java.util.List;

import com.mlnx.chronic.entity.TPhoneValid;

public interface TPhoneValidMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TPhoneValid record);

    TPhoneValid selectByPrimaryKey(Integer id);

    List<TPhoneValid> selectAll();

    int updateByPrimaryKey(TPhoneValid record);

	TPhoneValid selectByPhone(String phone);
}