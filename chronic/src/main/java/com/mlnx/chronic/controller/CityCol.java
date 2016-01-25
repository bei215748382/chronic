package com.mlnx.chronic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mlnx.chronic.entity.TCity;
import com.mlnx.chronic.service.CityService;
import com.mlnx.chronic.util.ChronicResponse;
import com.mlnx.chronic.util.EnumCollection.ResponseCode;


@Controller
@RequestMapping(value = "/cities")
public class CityCol {

	@Autowired
	private CityService cityService;

	@RequestMapping(value = "all.do")
	@ResponseBody
	public List<TCity> findAll() {
		return cityService.findAll();
	}

	@RequestMapping(value = "register.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ChronicResponse register(@RequestBody TCity city) {
		cityService.save(city);
		ChronicResponse response = new ChronicResponse(ResponseCode.CITY_REGISTER_SUCCESS);
		return response;
	}

	@RequestMapping(value = "find/province/{id}/cities.do", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public List<TCity> findByProvinceId(@PathVariable int id) {
		return cityService.findByProvinceId(id);
	}
	
	@RequestMapping(value = "find/name.do", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public List<TCity> findByName(@RequestBody String name) {
		return cityService.findByName(name);
	}
}
