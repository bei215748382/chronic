package com.mlnx.chronic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/report")
public class ReportCol {
	
	@RequestMapping("/index")
	public String index() throws Exception {
		return "report/demo";
	}
}
