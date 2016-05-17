package com.mlnx.chronic.service;

import java.util.Map;

import com.mlnx.chronic.entity.TPatientBloodSugar;
import com.mlnx.chronic.util.ChronicResponse;

public interface BloodSugarService {
	
	//获取病人第一条数据
	public Map<String, Object> findByPatientIdLimitOne(int patientId);
	
	//获取病人总共的记录数
	public Map<String, Object> findCountByPatientId(int patientId);
	
	//添加血糖
	public ChronicResponse addBloodSugar(TPatientBloodSugar bloodSugar);

	//根据病人id和时间范围查找血糖值
	public Map<String,Object> searchBloodSugarWithTimeRange(Long startTime,
			Long endTime,Integer id);

	//根据时间点和限制条数获取血糖数据
	public Map<String, Object> searchLastBloodSugar(Integer patientId,
			Long date, Integer limit);

	//同步某个时间段的数据
	public Map<String, Object> synBloodSugarWithTimeRange(Long startTime,
			Long endTime, Integer id,Long timestamp);

}
