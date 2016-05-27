package com.mlnx.chronic.mapper;

import com.mlnx.chronic.entity.TPatientMedcine;
import com.mlnx.chronic.vo.Prescription;

import java.util.List;

public interface TPatientMedcineMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TPatientMedcine record);

    TPatientMedcine selectByPrimaryKey(Integer id);

    List<TPatientMedcine> selectAll();

    int updateByPrimaryKey(TPatientMedcine record);

	List<Prescription> getMedcine(TPatientMedcine tpm);
}