package com.mlnx.chronic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mlnx.chronic.entity.TFeedback;
import com.mlnx.chronic.service.FeedbackService;
import com.mlnx.chronic.util.ChronicResponse;

@Controller
@RequestMapping(value = "/feedback")
public class FeedBackCol {
	@Autowired
	private FeedbackService tFeedbackService;
	
	@RequestMapping(value = "index")
	public ModelAndView getAllFeedback(){
		List<TFeedback> feedbacks = tFeedbackService.selectAll();
		ModelAndView modelAndView = new ModelAndView("");
		return null;
	} 
	
	@RequestMapping(value = "regist")
	@ResponseBody
	public ChronicResponse regist(@RequestBody TFeedback fFeedback) throws Exception {
		return tFeedbackService.registFeedback(fFeedback);
	}
	
}
