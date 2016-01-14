package com.mlnx.chronic.service;

import java.util.Map;

public interface BloodSugarService {
	//获取病人第一条数据
	public Map<String, Object> findByPatientIdLimitOne(int patientId);
	//获取病人总共的记录数
	public Map<String, Object> findCountByPatientId(int patientId);
}
