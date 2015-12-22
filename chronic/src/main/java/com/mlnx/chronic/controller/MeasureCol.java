package com.mlnx.chronic.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mlnx.chronic.entity.BloodSugar;
import com.mlnx.chronic.repo.BloodSugarRepository;
import com.mlnx.springmvc.service.BloodPressureService;
import com.mlnx.springmvc.service.BloodSugarService;
import com.mlnx.springmvc.util.StringUtil;
import com.mlnx.springmvc.util.EnumCollection.ResponseCode;

@Controller
@RequestMapping(value = "/measure")
public class MeasureCol {

	private static final Logger logger = LoggerFactory
			.getLogger(MeasureCol.class);

	@Autowired
	private BloodSugarRepository bloodSugarRepository;
	
	@Autowired
	private BloodPressureService bloodPressureService;
	
	@Autowired
	private BloodSugarService bloodSugarService;

	/**
	 * 获取最后一次血糖数据
	 * 
	 * @param patientId
	 * @param limit
	 * @param date
	 * @return
	 */
	@RequestMapping(value = "search/last/bloodSugar", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> searchLastBloodSugar2(
			@RequestHeader("patientId") int patientId,
			@RequestHeader("limit") int limit, @RequestHeader("date") long date) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Iterator<BloodSugar> it = bloodSugarRepository
					.findLastByPatientIdAndStateAndDate(patientId, new Date(
							date), limit);
			map.put(StringUtil.responseCode,
					ResponseCode.FIND_BLOOD_SUGAR_LAST_INFO_SUCCESS.getCode());
			map.put(StringUtil.responseMsg,
					ResponseCode.FIND_BLOOD_SUGAR_LAST_INFO_SUCCESS.getMsg());
			map.put(StringUtil.responseObjList, it);
			return map;
		} catch (Exception e) {
			logger.error(e.getMessage());
			map.put(StringUtil.responseCode,
					ResponseCode.FIND_BLOOD_SUGAR_LAST_INFO_ERROR.getCode());
			map.put(StringUtil.responseMsg,
					ResponseCode.FIND_BLOOD_SUGAR_LAST_INFO_ERROR.getMsg());
			return map;
		}
	}
	
	@RequestMapping(value="search/first/bloodPressure",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> searchFirstBloodPressure(@RequestHeader("patientId") int patientId){
		return bloodPressureService.findByPatientIdLimitOne(patientId);
	}
	
	@RequestMapping(value="search/bloodPressure/count",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> searchBloodPressureCount(@RequestHeader("patientId") int patientId){
		return bloodPressureService.findCountByPatientId(patientId);
	}
	
	@RequestMapping(value="search/first/bloodSugar",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> searchFirstBloodSugar(@RequestHeader("patientId") int patientId){
		return bloodSugarService.findByPatientIdLimitOne(patientId);
	}
	
	@RequestMapping(value="search/bloodSugar/count",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> searchBloodSugarCount(@RequestHeader("patientId") int patientId){
		return bloodSugarService.findCountByPatientId(patientId);
	}

}
