package com.mlnx.chronic.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mlnx.chronic.entity.TVisit;
import com.mlnx.chronic.service.VisitService;
import com.mlnx.chronic.util.ChronicResponse;
import com.mlnx.chronic.vo.VisitExt;

@Controller
@RequestMapping(value = "/visit")
public class VisitCol {

	@Autowired
	private VisitService visitService;
	
	@RequestMapping(value = "regist")
	@ResponseBody
	public ChronicResponse regist(@RequestHeader("doctorId") Integer doctorId,@RequestHeader("patientId") Integer patientId,@RequestBody List<Integer> tests,@RequestHeader("date") Long dateTime) throws Exception {
		TVisit visit = new TVisit();
		visit.setDoctorId(doctorId);
		visit.setPatientId(patientId);
		visit.setDate(new Date(dateTime));
		visit.setState(0);//0表示预约列表，
		return visitService.regist(visit, tests);
	}
	
	@RequestMapping(value = "edit")
	@ResponseBody
	public ChronicResponse edit(@RequestBody VisitExt visitExt) throws Exception {
		return visitService.edit(visitExt);
	}
	
	@RequestMapping(value = "search")
	@ResponseBody
	public Map<String,Object> search(@RequestHeader("doctorId") Integer doctorId) {
		return visitService.search(doctorId);
	}
	
	@RequestMapping(value = "search/history")
	@ResponseBody
	public Map<String,Object> searchHistory(@RequestHeader("doctorId") Integer doctorId) {
		return visitService.searchHistory(doctorId);
	}
	
	@RequestMapping(value = "add/report")
	@ResponseBody
	public ChronicResponse addReport(@RequestHeader("visitId") int visitId,@RequestHeader("condition") String condition,@RequestHeader("medicine") String medicine,@RequestParam(value = "file",required = false) CommonsMultipartFile[] files,HttpServletRequest request) {
		return visitService.addReport(visitId,condition,medicine,files,request);
	}
	
}
