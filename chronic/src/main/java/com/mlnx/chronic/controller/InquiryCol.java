package com.mlnx.chronic.controller;

import java.util.HashMap;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mlnx.chronic.service.InquiryService;

@Controller
@RequestMapping(value = "/inquiry")
public class InquiryCol {

	@Autowired
	private InquiryService inquiryService;

	/**
	 * 查找所有省
	 * 
	 * @return
	 */
	@RequestMapping(value = "all/province")
	@ResponseBody
	public Map<String, Object> findAllProvince() {
		return inquiryService.findAllProvince();
	}

	/**
	 * 根据省id或者省名称查找城市
	 * 
	 * @return
	 */
	@RequestMapping(value = "all/city/{id}")
	@ResponseBody
	public Map<String, Object> findAllCity(@PathVariable("id") String id) {
		return inquiryService.findAllCity(id);
	}

	/**
	 * 根据市id或者市名称查找城市
	 * 
	 * @return
	 */
	@RequestMapping(value = "all/hospital")
	@ResponseBody
	public Map<String, Object> findAllHospitalWithLevel(
			@PathParam("id") String id, @PathParam("level") String level) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("level", level);
		return inquiryService.findAllHospitalWithMap(map);
	}

	/**
	 * 根据医院id或者医院名称查找医生
	 * 
	 * @return
	 */
	@RequestMapping(value = "all/doc/by/hospital")
	@ResponseBody
	public Map<String, Object> findAllDocWithHospitalAndGroup(
			@PathParam("id") String id, @PathParam("group") String group) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("group", group);
		return inquiryService.findAllDocWithHospitalAndGroup(map);
	}

	/**
	 * 根据关键字查找，包括医生、医院、科室、疾病这4类关键字
	 * 
	 * @return
	 */
	@RequestMapping(value = "all/doc/like")
	@ResponseBody
	public Map<String, Object> findAllDocWithKey(@PathParam("keyStr") String keyStr) {
		return inquiryService.findAllDocWithKey(keyStr);
	}
	
	/**
	 * 根据城市
	 * 
	 * @return
	 */
	@RequestMapping(value = "all/doc/by/city")
	@ResponseBody
	public Map<String, Object> findAllDocByCity(@PathParam("cityId") String cityId,@PathParam("disease") String disease) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("cityId", cityId);
		map.put("disease", disease);
		return inquiryService.findAllDocByCity(map);
	}
	
}
