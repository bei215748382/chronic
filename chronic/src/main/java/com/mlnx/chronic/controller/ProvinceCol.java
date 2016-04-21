package com.mlnx.chronic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mlnx.chronic.entity.TProvince;
import com.mlnx.chronic.service.ProvinceService;
import com.mlnx.chronic.util.ChronicResponse;
import com.mlnx.chronic.util.EnumCollection.ResponseCode;

@Controller
@RequestMapping(value = "/provinces")
public class ProvinceCol {

	@Autowired
	private ProvinceService provinceService;

	@RequestMapping(value = "all")
	@ResponseBody
	public List<TProvince> findAll() {
		return provinceService.findAll();
	}
	
	@RequestMapping(value = "register",method=RequestMethod.POST, consumes="application/json",produces="application/json")
	@ResponseBody
	public ChronicResponse register(@RequestBody TProvince province){
		provinceService.save(province);
		ChronicResponse response = new ChronicResponse();
		response.setResponseCode(ResponseCode.PROVINCE_REGISTER_SUCCESS.getCode());
		response.setMsg(ResponseCode.PROVINCE_REGISTER_SUCCESS.getMsg());
		return response;
	}
	
	@RequestMapping(value = "find/name", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public List<TProvince> findByProvinceId(@RequestBody String name) {
		return provinceService.findByName(name);
	}
	
	@RequestMapping(value = "delete/{id}/province", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ChronicResponse delete(@PathVariable int id) {
		try{
			provinceService.delete(id);
			return new ChronicResponse(ResponseCode.PROVINCE_DELETE_SUCCESS);
		} catch(Exception e){
			e.printStackTrace();
			return new ChronicResponse(ResponseCode.PROVINCE_DELETE_ERROR);
		}
	}
}
