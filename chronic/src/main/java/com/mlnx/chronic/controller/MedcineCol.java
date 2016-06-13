package com.mlnx.chronic.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mlnx.chronic.entity.TPatientDinner;
import com.mlnx.chronic.entity.TPatientMedcine;
import com.mlnx.chronic.service.MedcineService;
import com.mlnx.chronic.util.ChronicResponse;
import com.mlnx.chronic.vo.medcine.TakeMedicineInfo;

@Controller
@RequestMapping(value = "/medcine")
public class MedcineCol {
	
	@Autowired
	private MedcineService medcineService;
	
	@RequestMapping(value = "setting/dinner")
	@ResponseBody
	public ChronicResponse settingDinner(@RequestBody TPatientDinner dinner) throws Exception {
		return medcineService.settingDinner(dinner);
	}
	
	@RequestMapping(value = "get/medcine")
	@ResponseBody
	public Map<String,Object> getMedcine(@RequestBody TPatientMedcine tpm) throws Exception {
		return medcineService.getMedcine(tpm);
	}
	
	@RequestMapping(value="get/medcine/prescription")
	@ResponseBody
	public Map<String,Object> getMedcinePrescription(@RequestParam(value="patientId") Integer id) throws Exception {
		return medcineService.getMedcinePrescription(id);
	}
	
	@RequestMapping(value="get/medcine/history")
	@ResponseBody
	public Map<String,Object> getMedcineHistory(@RequestParam(value="patientId") Integer id,@RequestParam(value="now") Long time,@RequestParam(value="limit",defaultValue="10") Integer limit) throws Exception {
		return medcineService.getMedcineHistory(id,time,limit);
	}
	
	@RequestMapping(value="save/take/medcine")
	@ResponseBody
	public ChronicResponse saveTakeMedcine(TakeMedicineInfo info) throws Exception {
		return medcineService.saveTakeMedcine(info);
	}
}
