package com.mlnx.chronic.service;

import java.util.Map;

import com.mlnx.chronic.entity.TPatientBloodPressure;
import com.mlnx.chronic.util.ChronicResponse;

public interface BloodPressureService {
	
	// 获取病人第一条数据
	public Map<String, Object> findByPatientIdLimitOne(int patientId);

	// 获取病人总共的记录数
	public Map<String, Object> findCountByPatientId(int patientId);

	// 添加血压值
	public ChronicResponse addBloodPressure(TPatientBloodPressure bloodPressure);

	// 根据病人id和时间范围查找血压值
	public Map<String, Object> getBloodPressureByPatientIdWithTimeRange(
			Long startTime, Long endTime, Integer id);

	//根据病人id和时间结束点限制条数，下拉刷新数据
	public Map<String, Object> getBloodPressureWithEndAndLimit(Integer limit,
			Long endTime, Integer id);

	//同步某个时间段的数据
	public Map<String, Object> synBloodPressureWithTimeRange(Long startTime,
			Long endTime, Integer id, Long timestamp);
}
