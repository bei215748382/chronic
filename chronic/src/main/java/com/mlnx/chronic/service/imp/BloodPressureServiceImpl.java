package com.mlnx.chronic.service.imp;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import com.mlnx.chronic.entity.BloodPressure;
import com.mlnx.chronic.repo.BloodPressureRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mlnx.springmvc.service.BloodPressureService;
import com.mlnx.springmvc.util.EnumCollection.ResponseCode;
import com.mlnx.springmvc.util.StringUtil;

@Service
public class BloodPressureServiceImpl implements BloodPressureService {

	@Autowired
	private BloodPressureRepository bloodPressureRepository;

	@Override
	public Map<String, Object> findByPatientIdLimitOne(int patientId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BloodPressure p = bloodPressureRepository
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
			Long count = bloodPressureRepository.findCountByPatientId(patientId);

			map.put(StringUtil.responseCode,
					ResponseCode.FIND_PATIENT_BLOOD_PRESSURE_COUNT_SUCCESS
							.getCode());
			map.put(StringUtil.responseMsg,
					ResponseCode.FIND_PATIENT_BLOOD_PRESSURE_COUNT_SUCCESS
							.getMsg());
			map.put(StringUtil.responseObj, count);
			return map;
		} catch (Exception e) {
			map.put(StringUtil.responseCode,
					ResponseCode.FIND_PATIENT_BLOOD_PRESSURE_COUNT_ERROR
							.getCode());
			map.put(StringUtil.responseMsg,
					ResponseCode.FIND_PATIENT_BLOOD_PRESSURE_COUNT_ERROR
							.getMsg());
			return map;
		}
	}

}
