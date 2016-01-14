package com.mlnx.chronic.service.imp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mlnx.chronic.entity.BloodSugar;
import com.mlnx.chronic.repo.BloodSugarRepository;
import com.mlnx.chronic.service.BloodSugarService;
import com.mlnx.chronic.util.StringUtil;
import com.mlnx.chronic.util.EnumCollection.ResponseCode;

@Service
public class BloodSugarServiceImpl implements BloodSugarService {
	
	@Autowired
	private BloodSugarRepository bloodSugarRepository;

	@Override
	public Map<String, Object> findByPatientIdLimitOne(int patientId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BloodSugar p = bloodSugarRepository
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
			Long count = bloodSugarRepository.findCountByPatientId(patientId);

			map.put(StringUtil.responseCode,
					ResponseCode.FIND_PATIENT_BLOOD_SUGAR_COUNT_SUCCESS
							.getCode());
			map.put(StringUtil.responseMsg,
					ResponseCode.FIND_PATIENT_BLOOD_SUGAR_COUNT_SUCCESS
							.getMsg());
			map.put(StringUtil.responseObj, count);
			return map;
		} catch (Exception e) {
			map.put(StringUtil.responseCode,
					ResponseCode.FIND_PATIENT_BLOOD_SUGAR_COUNT_ERROR
							.getCode());
			map.put(StringUtil.responseMsg,
					ResponseCode.FIND_PATIENT_BLOOD_SUGAR_COUNT_ERROR
							.getMsg());
			return map;
		}
	}

}
