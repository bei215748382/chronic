package com.mlnx.chronic.mapper;

import com.mlnx.chronic.entity.TDocPatient;
import java.util.List;

public interface TDocPatientMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TDocPatient record);

    TDocPatient selectByPrimaryKey(Integer id);

    List<TDocPatient> selectAll();

    int updateByPrimaryKey(TDocPatient record);
}