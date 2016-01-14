package com.mlnx.chronic.mapper;

import java.util.List;

import com.mlnx.chronic.entity.TGroupPatient;

public interface TGroupPatientMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TGroupPatient record);

    TGroupPatient selectByPrimaryKey(Integer id);

    List<TGroupPatient> selectAll();

    int updateByPrimaryKey(TGroupPatient record);

	List<TGroupPatient> findGroupPatients(int gid);

	Integer isExist(TGroupPatient groupPatient);
}