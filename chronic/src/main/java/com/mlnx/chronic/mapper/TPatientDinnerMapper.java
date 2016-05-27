package com.mlnx.chronic.mapper;

import com.mlnx.chronic.entity.TPatientDinner;
import java.util.List;

public interface TPatientDinnerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TPatientDinner record);

    TPatientDinner selectByPrimaryKey(Integer id);

    List<TPatientDinner> selectAll();

    int updateByPrimaryKey(TPatientDinner record);
}