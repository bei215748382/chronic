package com.mlnx.chronic.service.imp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mlnx.chronic.entity.TPatientBloodSugar;
import com.mlnx.chronic.mapper.TPatientBloodSugarMapper;
import com.mlnx.chronic.service.BloodSugarService;
import com.mlnx.chronic.util.ChronicResponse;
import com.mlnx.chronic.util.EnumCollection.ResponseCode;
import com.mlnx.chronic.util.StringUtil;
import com.mlnx.chronic.vo.DateCountVo;

@Service
public class BloodSugarServiceImpl implements BloodSugarService {
	
	@Autowired
	private TPatientBloodSugarMapper tPatientBloodSugarMapper;

	@Override
	public Map<String, Object> findByPatientIdLimitOne(int patientId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			TPatientBloodSugar p = tPatientBloodSugarMapper
					.findByPatientIdLimitOne(patientId);
			map.put(StringUtil.responseCode,
					ResponseCode.FIND_PATIENT_FIRST_BLOOD_SUGAR_SUCCESS
							.getCode());
			map.put(StringUtil.responseMsg,
					ResponseCode.FIND_PATIENT_FIRST_BLOOD_SUGAR_SUCCESS
							.getMsg());
			map.put(StringUtil.responseObj, p);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put(StringUtil.responseCode,
					ResponseCode.FIND_PATIENT_FIRST_BLOOD_SUGAR_ERROR
							.getCode());
			map.put(StringUtil.responseMsg,
					ResponseCode.FIND_PATIENT_FIRST_BLOOD_SUGAR_ERROR
							.getMsg());
			return map;
		}
	}

	@Override
	public Map<String, Object> findCountByPatientId(int patientId) {

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Long count = tPatientBloodSugarMapper.findCountByPatientId(patientId);
			map.put(StringUtil.responseCode,
					ResponseCode.FIND_PATIENT_BLOOD_SUGAR_COUNT_SUCCESS
							.getCode());
			map.put(StringUtil.responseMsg,
					ResponseCode.FIND_PATIENT_BLOOD_SUGAR_COUNT_SUCCESS
							.getMsg());
			map.put(StringUtil.responseObj, count);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put(StringUtil.responseCode,
					ResponseCode.FIND_PATIENT_BLOOD_SUGAR_COUNT_ERROR
							.getCode());
			map.put(StringUtil.responseMsg,
					ResponseCode.FIND_PATIENT_BLOOD_SUGAR_COUNT_ERROR
							.getMsg());
			return map;
		}
	}

	@Override
	public ChronicResponse addBloodSugar(TPatientBloodSugar bloodSugar) {
		try{
			bloodSugar.setTimestamp(new Date());//设置时间戳，同步用。用mysql可以在数据库设计的时候，设置timestamp为非空，那么没有设置也会插入当前时间
			tPatientBloodSugarMapper.insert(bloodSugar);
			return new ChronicResponse(ResponseCode.ADD_BLOOD_SUGAR_SUCCESS);
		} catch(Exception e){
			e.printStackTrace();
		}
		return new ChronicResponse(ResponseCode.ADD_BLOOD_SUGAR_ERROR);
	}

	@Override
	public Map<String,Object> searchBloodSugarWithTimeRange(Long startTime,
			Long endTime, Integer id) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Map<String,Object> parmMap = new HashMap<String,Object>();
			parmMap.put("startTime", new Date(startTime));
			parmMap.put("endTime", new Date(endTime));
			parmMap.put("patientId", id);
			List<TPatientBloodSugar> list = tPatientBloodSugarMapper.searchBloodSugarWithTimeRange(parmMap);
			map.put(StringUtil.responseCode, ResponseCode.GET_BLOOD_SUGAR_BY_PATIENT_ID_WITH_TIME_RANGE_SUCCESS.getCode());
			map.put(StringUtil.responseMsg, ResponseCode.GET_BLOOD_SUGAR_BY_PATIENT_ID_WITH_TIME_RANGE_SUCCESS.getMsg());
			map.put(StringUtil.responseObjList, list);
		} catch(Exception e){
			e.printStackTrace();
			map.put(StringUtil.responseCode, ResponseCode.GET_BLOOD_SUGAR_BY_PATIENT_ID_WITH_TIME_RANGE_ERROR.getCode());
			map.put(StringUtil.responseMsg, ResponseCode.GET_BLOOD_SUGAR_BY_PATIENT_ID_WITH_TIME_RANGE_ERROR.getMsg());
		}
		return map;
	}

	@Override
	public Map<String, Object> searchLastBloodSugar(Integer patientId,
			Long date, Integer limit,String state) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Map<String,Object> parmMap = new HashMap<String,Object>();
			parmMap.put("limit", limit);
			parmMap.put("endTime", new Date(date));
			parmMap.put("patientId", patientId);
			parmMap.put("state", state);
			List<TPatientBloodSugar> list = tPatientBloodSugarMapper.searchLastBloodSugar(parmMap);
			map.put(StringUtil.responseCode, ResponseCode.GET_BLOOD_SUGAR_BY_PATIENT_ID_WITH_ENDTIME_AND_LIMIT_SUCCESS.getCode());
			map.put(StringUtil.responseMsg, ResponseCode.GET_BLOOD_SUGAR_BY_PATIENT_ID_WITH_ENDTIME_AND_LIMIT_SUCCESS.getMsg());
			map.put(StringUtil.responseObjList, list);
		} catch(Exception e){
			e.printStackTrace();
			map.put(StringUtil.responseCode, ResponseCode.GET_BLOOD_SUGAR_BY_PATIENT_ID_WITH_ENDTIME_AND_LIMIT_ERROR.getCode());
			map.put(StringUtil.responseMsg, ResponseCode.GET_BLOOD_SUGAR_BY_PATIENT_ID_WITH_ENDTIME_AND_LIMIT_ERROR.getMsg());
		}
		return map;
	}

	@Override
	public Map<String, Object> synBloodSugarWithTimeRange(Long startTime,
			Long endTime, Integer id, Long timestamp) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Map<String,Object> parmMap = new HashMap<String,Object>();
			parmMap.put("startTime", new Date(startTime));
			parmMap.put("endTime", new Date(endTime));
			parmMap.put("patientId", id);
			parmMap.put("timestamp", new Date(timestamp));
			List<TPatientBloodSugar> list = tPatientBloodSugarMapper.synBloodSugarWithTimeRange(parmMap);
			map.put(StringUtil.responseCode, ResponseCode.SYN_BLOOD_SUGAR_BY_PATIENT_ID_WITH_TIME_RANGE_SUCCESS.getCode());
			map.put(StringUtil.responseMsg, ResponseCode.SYN_BLOOD_SUGAR_BY_PATIENT_ID_WITH_TIME_RANGE_SUCCESS.getMsg());
			map.put(StringUtil.responseObjList, list);
		} catch(Exception e){
			e.printStackTrace();
			map.put(StringUtil.responseCode, ResponseCode.SYN_BLOOD_SUGAR_BY_PATIENT_ID_WITH_TIME_RANGE_ERROR.getCode());
			map.put(StringUtil.responseMsg, ResponseCode.SYN_BLOOD_SUGAR_BY_PATIENT_ID_WITH_TIME_RANGE_ERROR.getMsg());
		}
		return map;
	}

	@Override
	public Map<String, Object> getBloodSugarMonth(Integer patientId) {
		Map<String, Object> map = new HashMap<String,Object>();
		try{
			List<DateCountVo> list = tPatientBloodSugarMapper.getBloodSugarMonth(patientId);
			map.put(StringUtil.responseCode, ResponseCode.GET_BLOOD_SUGAR_MONTH_COUNT_BY_PATIENT_ID_SUCCESS.getCode());
			map.put(StringUtil.responseMsg, ResponseCode.GET_BLOOD_SUGAR_MONTH_COUNT_BY_PATIENT_ID_SUCCESS.getMsg());
			map.put(StringUtil.responseObjList, list);
		} catch(Exception e){
			e.printStackTrace();
			map.put(StringUtil.responseCode, ResponseCode.GET_BLOOD_SUGAR_MONTH_COUNT_BY_PATIENT_ID_ERROR.getCode());
			map.put(StringUtil.responseMsg, ResponseCode.GET_BLOOD_SUGAR_MONTH_COUNT_BY_PATIENT_ID_ERROR.getMsg());
		}
		return map;
	}

	@Override
	public Map<String, Object> getBloodSugarDate(Integer patientId, Date start) {
		Map<String, Object> map = new HashMap<String,Object>();
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("patientId", patientId);
		paramMap.put("date", start);
		try{
			List<DateCountVo> list = tPatientBloodSugarMapper.getBloodSugarDate(paramMap);
			map.put(StringUtil.responseCode, ResponseCode.GET_BLOOD_SUGAR_DATE_COUNT_BY_PATIENT_ID_SUCCESS.getCode());
			map.put(StringUtil.responseMsg, ResponseCode.GET_BLOOD_SUGAR_DATE_COUNT_BY_PATIENT_ID_SUCCESS.getMsg());
			map.put(StringUtil.responseObjList, list);
		} catch(Exception e){
			e.printStackTrace();
			map.put(StringUtil.responseCode, ResponseCode.GET_BLOOD_SUGAR_DATE_COUNT_BY_PATIENT_ID_ERROR.getCode());
			map.put(StringUtil.responseMsg, ResponseCode.GET_BLOOD_SUGAR_DATE_COUNT_BY_PATIENT_ID_ERROR.getMsg());
		}
		return map;
	}


}
