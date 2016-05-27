package com.mlnx.chronic.mapper;

import com.mlnx.chronic.entity.TExercisePrescription;

import java.util.List;

public interface TExercisePrescriptionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TExercisePrescription record);

    TExercisePrescription selectByPrimaryKey(Integer id);

    List<TExercisePrescription> selectAll();

    int updateByPrimaryKey(TExercisePrescription record);

	List<TExercisePrescription> selectByPatientId(Integer patientId);
}