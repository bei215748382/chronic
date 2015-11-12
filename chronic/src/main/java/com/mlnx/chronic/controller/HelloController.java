/**
 * 
 */
/**
 * @author bwh<bruce.bei@nbmlnx.com>
 * 2015年10月29日
 *
 */
package com.mlnx.chronic.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mlnx.chronic.entity.BloodPressure;
import com.mlnx.chronic.entity.TReport;
import com.mlnx.chronic.entity.TUser;
import com.mlnx.chronic.entity.TUserExt;
import com.mlnx.chronic.mapper.TReportMapper;
import com.mlnx.chronic.mapper.TUserExtMapper;
import com.mlnx.chronic.mapper.TUserMapper;
import com.mlnx.chronic.repo.BloodPressureRepository;
import com.mlnx.chronic.vo.UserVo;

@Controller
public class HelloController {
	@Autowired
	private TUserMapper userMapper;
	
	@Autowired
	private TUserExtMapper tUserExtMapper;

	@Autowired
	private TReportMapper tReportMapper;
	
	@Autowired
	private BloodPressureRepository bloodPressureRepository;

	private static final Logger logger = LoggerFactory
			.getLogger(HelloController.class);
	
	@RequestMapping(value = "/report/index/{id}", method = RequestMethod.GET)
	public ModelAndView reportIndex(@PathVariable int id) {
		TUserExt user = tUserExtMapper.selectByPrimaryKey(id);
		TReport report = tReportMapper.selectByPrimaryKey(id);
		ModelAndView modelAndView = new ModelAndView("report/index");
		modelAndView.addObject("userExt", user);
		modelAndView.addObject("report", report);
		return modelAndView;
	}

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello(TUser user, HttpServletRequest req) {
		req.setAttribute("name", "年后");
		return "hello";
	}

	@RequestMapping(value = "/hello", method = RequestMethod.POST)
	public String doHello(TUser user) {
		logger.debug("name:" + user.getPhone());
		userMapper.insert(user);
		return "redirect:/hello.do";
	}

	@RequestMapping(value = "/{id}/findReports", method = RequestMethod.GET)
	@ResponseBody
	public List<TReport> findReports(@PathVariable int id) {
		logger.info(String.format("The user id is %s", id));
		return tReportMapper.selectByUserId(id);
	}

	@RequestMapping(value = "/{id}/findUserReports", method = RequestMethod.GET)
	@ResponseBody
	public UserVo findUserReports(@PathVariable int id) {
		logger.info(String.format("The user id is %s", id));
		return userMapper.selectTReportsByPrimaryKey(id);
	}
	
	@RequestMapping(value = "/bloodPressure", method = RequestMethod.GET)
	@ResponseBody
	public List<BloodPressure> bloodPressure() {
		List<BloodPressure> list = bloodPressureRepository.findAllOrderedById();
		return list;
	}


}