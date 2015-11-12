package com.mlnx.chronic.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mlnx.chronic.entity.TReport;
import com.mlnx.chronic.entity.TReportContent;
import com.mlnx.chronic.entity.TUser;
import com.mlnx.chronic.entity.TUserExt;
import com.mlnx.chronic.mapper.TReportContentMapper;
import com.mlnx.chronic.mapper.TReportMapper;
import com.mlnx.chronic.mapper.TUserExtMapper;
import com.mlnx.chronic.mapper.TUserMapper;
import com.mlnx.springmvc.service.UserService;
import com.mlnx.springmvc.util.FileUtil;

@Controller
@RequestMapping(value = "/admin")
@Transactional(rollbackFor = Exception.class)
public class AdminCol {

	@Autowired
	private TUserMapper tUserMapper;

	@Autowired
	private TUserExtMapper tUserExtMapper;

	@Autowired
	private TReportMapper tReportMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private TReportContentMapper tReportContentMapper;

	// 登陆提交
	// userid：用户账号，pwd：密码
	@RequestMapping("/login")
	public String loginsubmit(HttpSession session, String username,
			String password) throws Exception {

		// 向session记录用户身份信息
		// TODO 进行用户登入验证、权限验证等等
		session.setAttribute("activeUser", username);
		return "redirect:index.do";
	}

	// 退出
	@RequestMapping("/logout")
	public String logout(HttpSession session) throws Exception {

		// session过期
		session.invalidate();

		return "redirect:login.do";
	}

	// 注册页面
	@RequestMapping("/register")
	public String register() throws Exception {
		return "admin/register";
	}

	// 注册
	@RequestMapping("/registerUser")
	public String registerUser(TUser user) throws Exception {
		userService.registUser(user);
		return "admin/login";
	}

	@RequestMapping(value = "index")
	public String index() {
		return "admin/index";
	}

	@ModelAttribute("users")
	public List<TUser> getUsers() {
		return tUserMapper.selectAll();
	}

	// --------------------------------------- 用户信息管理
	// ---------------------------------------------------------------------
	@RequestMapping(value = "users_info")
	public ModelAndView users_info(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("admin/ajax/users_info");
		List<TUser> users = tUserMapper.selectAll();
		List<TUserExt> userExts = tUserExtMapper.selectAll();
		modelAndView.addObject("users", users);
		modelAndView.addObject("userExts", userExts);
		return modelAndView;
	}

	@RequestMapping(value = "user_add")
	public String user_add() {
		return "admin/ajax/user_add";
	}

	/**
	 * 手机注册已存在验证
	 * 
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = "findByPhone")
	@ResponseBody
	public Map<String, Boolean> findByPhone(String phone) {
		TUser user = tUserMapper.selectByPhone(phone);
		boolean valid = false;
		if (user == null) {
			valid = true;
		}
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("valid", valid);
		return map;
	}

	@RequestMapping(value = "user_add_json")
	public void user_add_json(TUser user, HttpServletResponse response)
			throws Exception {
		userService.registUser(user);
		response.sendRedirect("index.do#users_info.do");
	}

	@RequestMapping(value = "user_delete")
	public String user_delete(int id) {
		tUserMapper.deleteByPrimaryKey(id);
		return "admin/index";
	}

	@RequestMapping(value = "user_edit")
	public String user_edit() {
		return "admin/ajax/user_edit";
	}

	@RequestMapping(value = "user_edit_json")
	public void user_edit_json(TUser user, HttpServletResponse response)
			throws IOException {
		tUserMapper.updateByPrimaryKey(user);
		response.sendRedirect("index.do#users_info.do");
	}

	@RequestMapping(value = "user_ext_add")
	public String user_ext_add() {
		return "admin/ajax/user_ext_add";
	}

	@RequestMapping(value = "user_ext_edit")
	public ModelAndView user_ext_edit(int id) {
		TUserExt userExt = tUserExtMapper.selectByPrimaryKey(id);
		ModelAndView modelAndView = new ModelAndView("admin/ajax/user_ext_edit");
		modelAndView.addObject("userExt", userExt);
		return modelAndView;
	}

	@RequestMapping(value = "user_ext_add_json")
	public void user_ext_add_json(MultipartFile file, TUserExt userExt,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String pic = FileUtil.savePic(request, file);
		userExt.setPic(pic);
		tUserExtMapper.insert(userExt);
		response.sendRedirect("index.do#users_info.do");
	}

	@RequestMapping(value = "user_ext_edit_json")
	public void user_ext_edit_json(MultipartFile file, TUserExt userExt,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String pic = FileUtil.savePic(request, file);
		userExt.setPic(pic);
		tUserExtMapper.updateByPrimaryKey(userExt);
		response.sendRedirect("index.do#users_info.do");
	}

	@RequestMapping(value = "user_ext_delete")
	public String user_ext_delete(int id) {
		tUserExtMapper.deleteByPrimaryKey(id);
		return "admin/index";
	}

	// --------------------------------------- 报告管理
	// ---------------------------------------------------------------------
	@RequestMapping(value = "reports_info")
	public ModelAndView reports_info() {
		ModelAndView modelAndView = new ModelAndView("admin/ajax/reports_info");
		List<TReport> reports = tReportMapper.selectAll();
		modelAndView.addObject("reports", reports);
		return modelAndView;
	}

	@RequestMapping(value = "report_add")
	public ModelAndView report_add() {
		ModelAndView modelAndView = new ModelAndView("admin/ajax/report_add");
		List<TUser> users = tUserMapper.selectAll();
		modelAndView.addObject("users", users);
		return modelAndView;
	}

	@RequestMapping(value = "report_add_json")
	public void report_add_json(TReport report, HttpServletResponse response)
			throws IOException {
		tReportMapper.insert(report);
		response.sendRedirect("index.do#reports_info.do");
	}

	@RequestMapping(value = "report_edit")
	public ModelAndView report_edit(int id) {
		TReport report = tReportMapper.selectByPrimaryKey(id);
		ModelAndView modelAndView = new ModelAndView("admin/ajax/report_edit");
		modelAndView.addObject("report", report);
		return modelAndView;
	}

	@RequestMapping(value = "report_edit_json")
	public void report_edit_json(TReport report, HttpServletResponse response)
			throws IOException {
		tReportMapper.updateByPrimaryKey(report);
		response.sendRedirect("index.do#reports_info.do");
	}

	@RequestMapping(value = "report_delete")
	public void report_delete(int id, HttpServletResponse response)
			throws IOException {
		tReportMapper.deleteByPrimaryKey(id);
		response.sendRedirect("index.do#reports_info.do");
	}

	@RequestMapping(value = "report_content_info")
	public ModelAndView report_content_info(TReportContent reportContent) {
		ModelAndView modelAndView = new ModelAndView(
				"admin/ajax/report_content_info");
		List<TReportContent> tReportContents = tReportContentMapper.selectAll();
		modelAndView.addObject("tReportContents", tReportContents);
		return modelAndView;
	}

	@RequestMapping(value = "report_content_add")
	public String report_content_add() {
		return "admin/ajax/report_content_add";
	}

	@RequestMapping(value = "report_content_add_json")
	public void report_content_add_json(TReportContent reportContent,
			HttpServletResponse response) throws IOException {
		tReportContentMapper.insert(reportContent);
		response.sendRedirect("index.do#report_content_info.do");
	}

	@RequestMapping(value = "report_content_edit")
	public ModelAndView report_content_edit(int id) {
		TReportContent reportContent = tReportContentMapper
				.selectByPrimaryKey(id);
		ModelAndView modelAndView = new ModelAndView(
				"admin/ajax/report_content_edit");
		modelAndView.addObject("reportContent", reportContent);
		return modelAndView;
	}

	@RequestMapping(value = "report_content_edit_json")
	public void report_content_edit_json(TReportContent reportContent,
			HttpServletResponse response) throws IOException {
		tReportContentMapper.updateByPrimaryKey(reportContent);
		response.sendRedirect("index.do#report_content_info.do");
	}

	@RequestMapping(value = "report_content_delete")
	public void report_content_delete(int id, HttpServletResponse response)
			throws IOException {
		tReportContentMapper.deleteByPrimaryKey(id);
		response.sendRedirect("index.do#report_content_info.do");
	}
}
