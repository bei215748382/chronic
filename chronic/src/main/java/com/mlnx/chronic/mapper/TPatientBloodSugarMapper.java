package com.mlnx.chronic.mapper;

import java.util.List;
import java.util.Map;

import com.mlnx.chronic.entity.TPatientBloodSugar;

public interface TPatientBloodSugarMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TPatientBloodSugar record);

    TPatientBloodSugar selectByPrimaryKey(Integer id);

    List<TPatientBloodSugar> selectAll();

    int updateByPrimaryKey(TPatientBloodSugar record);

	TPatientBloodSugar findByPatientIdLimitOne(int patientId);

	Long findCountByPatientId(int patientId);

	List<TPatientBloodSugar> searchBloodSugarWithTimeRange(
			Map<String, Object> parmMap);

	List<TPatientBloodSugar> searchLastBloodSugar(Map<String, Object> parmMap);

	List<TPatientBloodSugar> synBloodSugarWithTimeRange(
			Map<String, Object> parmMap);
}