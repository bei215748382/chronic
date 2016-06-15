package com.mlnx.chronic.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mlnx.chronic.entity.TPatientDinner;
import com.mlnx.chronic.entity.TPatientMedicine;
import com.mlnx.chronic.service.MedicineService;
import com.mlnx.chronic.util.ChronicResponse;
import com.mlnx.chronic.vo.medicine.TakeMedicineInfo;

@Controller
@RequestMapping(value = "/medicine")
public class MedicineCol {
	
	@Autowired
	private MedicineService medicineService;
	
	@RequestMapping(value = "setting/dinner")
	@ResponseBody
	public ChronicResponse settingDinner(@RequestBody TPatientDinner dinner) throws Exception {
		return medicineService.settingDinner(dinner);
	}
	
	@RequestMapping(value="get/medicine/prescription")
	@ResponseBody
	public Map<String,Object> getmedicineePrescription(@RequestParam(value="patientId") Integer id) throws Exception {
		return medicineService.getMedicinePrescription(id);
	}
	
	@RequestMapping(value="get/medicine/history")
	@ResponseBody
	public Map<String,Object> getMedicineHistory(@RequestParam(value="patientId") Integer id,@RequestParam(value="now") Long time,@RequestParam(value="limit",defaultValue="10") Integer limit) throws Exception {
		return medicineService.getMedicineHistory(id,time,limit);
	}
	
	@RequestMapping(value="save/take/medicine")
	@ResponseBody
	public ChronicResponse saveTakeMedicine(TakeMedicineInfo info) throws Exception {
		return medicineService.saveTakeMedicine(info);
	}
}
