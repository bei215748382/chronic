package com.mlnx.chronic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mlnx.chronic.entity.TReport;
import com.mlnx.chronic.mapper.TReportMapper;

@Controller
@RequestMapping(value = "/report")
public class ReportCol {
	
	@Autowired
	private TReportMapper tReportMapper;
	
	@RequestMapping("/index")
	public String index()  {
		return "report/demo";
	}
	
	@RequestMapping("/view/{id}")
	public ModelAndView view(@PathVariable("id") int id)  {
		ModelAndView mav = new ModelAndView("report/view");
		TReport report = tReportMapper.selectLastByUserId(id);
		mav.addObject("data", report);
		return mav;
	}
}
