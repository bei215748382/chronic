package com.mlnx.chronic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mlnx.chronic.entity.THospital;
import com.mlnx.chronic.service.HospitalService;
import com.mlnx.chronic.util.ChronicResponse;
import com.mlnx.chronic.util.EnumCollection.ResponseCode;


@Controller
@RequestMapping(value = "/hospitals")
public class HospitalCol {

	@Autowired
	private HospitalService hospitalService;

	@RequestMapping(value = "all.do")
	@ResponseBody
	public List<THospital> findAll() {
		return hospitalService.findAll();
	}

	@RequestMapping(value = "register.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ChronicResponse register(@RequestBody THospital hospital) {
		hospitalService.save(hospital);
		ChronicResponse response = new ChronicResponse(ResponseCode.HOSPITAL_REGISTER_SUCCESS);
		return response;
	}

	@RequestMapping(value = "find/city/{id}/hospital.do", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public List<THospital> findByProvinceId(@PathVariable int id) {
		return hospitalService.findByCityId(id);
	}

	@RequestMapping(value = "find/name.do", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public List<THospital> findByName(@RequestBody String name) {
		return hospitalService.findByName(name);
	}
}
