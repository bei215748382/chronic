package com.mlnx.chronic.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mlnx.chronic.entity.TPatientDinner;
import com.mlnx.chronic.entity.TPatientMedcine;
import com.mlnx.chronic.entity.TVisit;
import com.mlnx.chronic.service.MedcineService;
import com.mlnx.chronic.util.ChronicResponse;

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
}
