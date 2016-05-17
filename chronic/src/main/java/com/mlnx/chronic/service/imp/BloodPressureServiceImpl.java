package com.mlnx.chronic.service.imp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mlnx.chronic.entity.TPatientBloodPressure;
import com.mlnx.chronic.mapper.TPatientBloodPressureMapper;
import com.mlnx.chronic.service.BloodPressureService;
import com.mlnx.chronic.util.ChronicResponse;
import com.mlnx.chronic.util.EnumCollection.ResponseCode;
import com.mlnx.chronic.util.StringUtil;

@Service
public class BloodPressureServiceImpl implements BloodPressureService {

	@Autowired
	private TPatientBloodPressureMapper tPatientBloodPressureMapper;

	@Override
	public Map<String, Object> findByPatientIdLimitOne(int patientId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			TPatientBloodPressure p = tPatientBloodPressureMapper
					.findByPatientIdLimitOne(patientId);
			map.put(StringUtil.responseCode,
					ResponseCode.FIND_PATIENT_FIRST_BLOOD_PRESSURE_SUCCESS
							.getCode());
			map.put(StringUtil.responseMsg,
					ResponseCode.FIND_PATIENT_FIRST_BLOOD_PRESSURE_SUCCESS
							.getMsg());
			map.put(StringUtil.responseObj, p);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put(StringUtil.responseCode,
					ResponseCode.FIND_PATIENT_FIRST_BLOOD_PRESSURE_ERROR
							.getCode());
			map.put(StringUtil.responseMsg,
					ResponseCode.FIND_PATIENT_FIRST_BLOOD_PRESSURE_ERROR
							.getMsg());
			return map;
		}
	}

	@Override
	public Map<String, Object> findCountByPatientId(int patientId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Long count = tPatientBloodPressureMapper
					.findCountByPatientId(patientId);
			map.put(StringUtil.responseCode,
					ResponseCode.FIND_PATIENT_BLOOD_PRESSURE_COUNT_SUCCESS
							.getCode());
			map.put(StringUtil.responseMsg,
					ResponseCode.FIND_PATIENT_BLOOD_PRESSURE_COUNT_SUCCESS
							.getMsg());
			map.put(StringUtil.responseObj, count);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put(StringUtil.responseCode,
					ResponseCode.FIND_PATIENT_BLOOD_PRESSURE_COUNT_ERROR
							.getCode());
			map.put(StringUtil.responseMsg,
					ResponseCode.FIND_PATIENT_BLOOD_PRESSURE_COUNT_ERROR
							.getMsg());
			return map;
		}
	}

	@Override
	public ChronicResponse addBloodPressure(TPatientBloodPressure bloodPressure) {
		try{
			bloodPressure.setTimestamp(new Date());//设置时间戳，同步用。用mysql可以在数据库设计的时候，设置timestamp为非空，那么没有设置也会插入当前时间
			tPatientBloodPressureMapper.insert(bloodPressure);
			return new ChronicResponse(ResponseCode.ADD_BLOOD_PRESSURE_SUCCESS);
		} catch(Exception e){
			e.printStackTrace();
		}
		return new ChronicResponse(ResponseCode.ADD_BLOOD_PRESSURE_ERROR);
	}

	@Override
	public Map<String, Object> getBloodPressureByPatientIdWithTimeRange(
			Long startTime, Long endTime, Integer id) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Map<String,Object> parmMap = new HashMap<String,Object>();
			parmMap.put("startTime", new Date(startTime));
			parmMap.put("endTime", new Date(endTime));
			parmMap.put("patientId", id);
			List<TPatientBloodPressure> list = tPatientBloodPressureMapper.getBloodPressureByPatientIdWithTimeRange(parmMap);
			map.put(StringUtil.responseCode, ResponseCode.GET_BLOOD_PRESSURE_BY_PATIENT_ID_WITH_TIME_RANGE_SUCCESS.getCode());
			map.put(StringUtil.responseMsg, ResponseCode.GET_BLOOD_PRESSURE_BY_PATIENT_ID_WITH_TIME_RANGE_SUCCESS.getMsg());
			map.put(StringUtil.responseObjList, list);
		} catch(Exception e){
			e.printStackTrace();
			map.put(StringUtil.responseCode, ResponseCode.GET_BLOOD_PRESSURE_BY_PATIENT_ID_WITH_TIME_RANGE_ERROR.getCode());
			map.put(StringUtil.responseMsg, ResponseCode.GET_BLOOD_PRESSURE_BY_PATIENT_ID_WITH_TIME_RANGE_ERROR.getMsg());
		}
		return map;
	}

	@Override
	public Map<String, Object> getBloodPressureWithEndAndLimit(Integer limit,
			Long endTime, Integer id) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Map<String,Object> parmMap = new HashMap<String,Object>();
			parmMap.put("limit", limit);
			parmMap.put("endTime", new Date(endTime));
			parmMap.put("patientId", id);
			List<TPatientBloodPressure> list = tPatientBloodPressureMapper.getBloodPressuereWithEndAndLimit(parmMap);
			map.put(StringUtil.responseCode, ResponseCode.GET_BLOOD_PRESSURE_BY_PATIENT_ID_WITH_ENDTIME_AND_LIMIT_SUCCESS.getCode());
			map.put(StringUtil.responseMsg, ResponseCode.GET_BLOOD_PRESSURE_BY_PATIENT_ID_WITH_ENDTIME_AND_LIMIT_SUCCESS.getMsg());
			map.put(StringUtil.responseObjList, list);
		} catch(Exception e){
			e.printStackTrace();
			map.put(StringUtil.responseCode, ResponseCode.GET_BLOOD_PRESSURE_BY_PATIENT_ID_WITH_ENDTIME_AND_LIMIT_ERROR.getCode());
			map.put(StringUtil.responseMsg, ResponseCode.GET_BLOOD_PRESSURE_BY_PATIENT_ID_WITH_ENDTIME_AND_LIMIT_ERROR.getMsg());
		}
		return map;
	}

	@Override
	public Map<String, Object> synBloodPressureWithTimeRange(Long startTime,
			Long endTime, Integer id, Long timestamp) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Map<String,Object> parmMap = new HashMap<String,Object>();
			parmMap.put("startTime", new Date(startTime));
			parmMap.put("endTime", new Date(endTime));
			parmMap.put("patientId", id);
			parmMap.put("timestamp", new Date(timestamp));
			List<TPatientBloodPressure> list = tPatientBloodPressureMapper.synBloodPressureWithTimeRange(parmMap);
			map.put(StringUtil.responseCode, ResponseCode.SYN_BLOOD_PRESSURE_BY_PATIENT_ID_WITH_TIME_RANGE_SUCCESS.getCode());
			map.put(StringUtil.responseMsg, ResponseCode.SYN_BLOOD_PRESSURE_BY_PATIENT_ID_WITH_TIME_RANGE_SUCCESS.getMsg());
			map.put(StringUtil.responseObjList, list);
		} catch(Exception e){
			e.printStackTrace();
			map.put(StringUtil.responseCode, ResponseCode.SYN_BLOOD_PRESSURE_BY_PATIENT_ID_WITH_TIME_RANGE_ERROR.getCode());
			map.put(StringUtil.responseMsg, ResponseCode.SYN_BLOOD_PRESSURE_BY_PATIENT_ID_WITH_TIME_RANGE_ERROR.getMsg());
		}
		return map;
	}

}
