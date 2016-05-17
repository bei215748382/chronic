package com.mlnx.chronic.mapper;

import com.mlnx.chronic.entity.TPatientBloodPressure;

import java.util.List;
import java.util.Map;

public interface TPatientBloodPressureMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TPatientBloodPressure record);

    TPatientBloodPressure selectByPrimaryKey(Integer id);

    List<TPatientBloodPressure> selectAll();

    int updateByPrimaryKey(TPatientBloodPressure record);

	TPatientBloodPressure findByPatientIdLimitOne(int patientId);

	Long findCountByPatientId(int patientId);

	List<TPatientBloodPressure> getBloodPressureByPatientIdWithTimeRange(
			Map<String, Object> parmMap);

	List<TPatientBloodPressure> getBloodPressuereWithEndAndLimit(
			Map<String, Object> parmMap);

	List<TPatientBloodPressure> synBloodPressureWithTimeRange(
			Map<String, Object> parmMap);
}