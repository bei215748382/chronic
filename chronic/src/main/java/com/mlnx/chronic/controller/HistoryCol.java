package com.mlnx.chronic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mlnx.chronic.repo.BloodSugarRepository;

@Controller
@RequestMapping(value = "/history")
public class HistoryCol {
	@Autowired
	private BloodSugarRepository bloodSugarRepository;
	
	
}
