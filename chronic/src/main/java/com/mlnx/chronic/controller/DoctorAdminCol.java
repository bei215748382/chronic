package com.mlnx.chronic.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mlnx.chronic.service.UserService;
import com.mlnx.chronic.util.EnumCollection.ResponseCode;
import com.mlnx.chronic.util.StringUtil;

@Controller
@RequestMapping(value = "/doctor")
public class DoctorAdminCol {

	@Autowired
	private UserService userService;

	private static final Logger log = LoggerFactory
			.getLogger(DoctorAdminCol.class);

	/**
	 * 根据手机号、密码登入
	 * 
	 * @param phone
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "login")
	public String login(HttpSession session, String phone,
			String password) {
		Map<String, String> map = userService.login(phone, password);
		String resonseCode = map.get(StringUtil.responseCode);
		if(resonseCode.equals(ResponseCode.LOGIN_SUCCESS.getCode())){
			session.setAttribute(StringUtil.docAdminLogin, phone);
		} else {
			
		}
		return null;
	}
}
