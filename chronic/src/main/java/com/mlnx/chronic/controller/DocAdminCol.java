package com.mlnx.chronic.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mlnx.chronic.entity.TDevice;
import com.mlnx.chronic.entity.TUser;
import com.mlnx.chronic.entity.TUserDoc;
import com.mlnx.chronic.entity.TUserExt;
import com.mlnx.chronic.entity2.TAdminUser;
import com.mlnx.chronic.mapper2.TAdminUserMapper;
import com.mlnx.chronic.service.DocAdminService;
import com.mlnx.chronic.util.FileUtil;
import com.mlnx.chronic.util.StringUtil;
import com.mlnx.chronic.vo.DeviceVo;
import com.mlnx.chronic.vo.DocVo;
import com.mlnx.chronic.vo.PatientVo;
import com.mlnx.chronic.vo.ServiceAddressData;
import com.mlnx.chronic.vo.ServiceData;
import com.mlnx.chronic.vo.ServiceDeviceData;
import com.mlnx.chronic.vo.ServiceVo;

@Controller
@RequestMapping(value = "/doc")
public class DocAdminCol {

	@Autowired
	private DocAdminService docService;

	@Autowired
	private TAdminUserMapper tAdminUserMapper;
	
	private static final Logger log = LoggerFactory
			.getLogger(DocAdminCol.class);

	// 登陆提交
	// userid：用户账号，pwd：密码
	@RequestMapping("/login")
	public String loginsubmit(HttpSession session, String username,
			String password) throws Exception {

		// 向session记录用户身份信息
		if (username != null && username != "") {
			TAdminUser user = tAdminUserMapper.selectByUsername(username);
			if (user != null && user.getPassword().equals(password)) {
				if (user.getPermission() == StringUtil.LOGIN_PERMISSION
						|| user.getPermission() == StringUtil.LOGIN_DOC_PERMISSION) {
					session.setAttribute(StringUtil.docAdminLogin, user);
				} else {
					log.info(StringUtil.LOGIN_NO_PERMISSION);
				}
			} else {
				log.info(StringUtil.LOGIN_PASSWORD_ERROR);
			}
		}
		return "redirect:index.do";
	}

	// 退出
	@RequestMapping("/logout")
	public String logout(HttpSession session) throws Exception {

		// session过期
		session.invalidate();

		return "redirect:login.do";
	}

	@RequestMapping(value = "index")
	public String index() {
		return "doc/index";
	}

	// ---------------------医生信息管理-------------------------//
	@RequestMapping(value = "users_info")
	public ModelAndView users_info() {
		ModelAndView mav = new ModelAndView("doc/ajax/users_info");
		List<DocVo> doctors = docService.findAllDoc();
		List<TUserExt> patients = docService.findAllPatients();
		mav.addObject("doctors", doctors);
		mav.addObject("patients", patients);
		return mav;
	}

	@RequestMapping(value = "user_doc_add")
	public String user_doc_add() {
		return "doc/ajax/user_doc_add";
	}

	@RequestMapping(value = "user_doc_edit")
	public ModelAndView user_doc_edit(Integer id) {
		ModelAndView mav = new ModelAndView("doc/ajax/user_doc_edit");
		DocVo doc = docService.findDocById(id);
		mav.addObject("doctor", doc);
		return mav;
	}

	/**
	 * 注册医生账号、包括上传头像
	 * 
	 * @param doctor
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "doctor_add_json", method = RequestMethod.POST)
	public void doctor_add_json(MultipartFile file, HttpServletRequest request,
			HttpServletResponse response, TUserDoc doctor, TUser user)
			throws Exception {
		if (file != null && file.getOriginalFilename() != "") {
			String pic = FileUtil.savePic(request, file);
			doctor.setPic(pic);
		}
		docService.regist(doctor, user);
		response.sendRedirect("index#users_info");
	}

	@RequestMapping(value = "find/regist/phone", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Boolean> findByRegistPhone(String phone) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		TUserDoc d = docService.findByRegistPhone(phone);
		boolean valid = true;
		if (d != null) {
			valid = false;
		} else {
			valid = true;
		}
		map.put("valid", valid);
		return map;
	}

	@RequestMapping(value = "doctor_edit_json")
	public void doctor_edit_json(MultipartFile file,
			HttpServletRequest request, HttpServletResponse response,
			TUserDoc doctor) throws IOException {
		if (file != null && file.getOriginalFilename() != "") {
			String pic = FileUtil.savePic(request, file);
			doctor.setPic(pic);
		}
		docService.updateDoc(doctor);
		response.sendRedirect("index#users_info");
	}

	// -------------------服务信息-------------------------//
	@RequestMapping(value = "services_info")
	public ModelAndView services_info() {
		ModelAndView mav = new ModelAndView("doc/ajax/services_info");
		List<ServiceVo> services = docService.findAllService();
		mav.addObject("services", services);
		return mav;
	}

	// ----------------------数据信息--------------------//
	@RequestMapping(value = "data_info")
	public ModelAndView data_info() {
		ModelAndView mav = new ModelAndView("doc/ajax/data_info");
		List<ServiceData> serviceData = docService
				.findAllServiceGroupByDoctorId();
		List<ServiceAddressData> serviceAddressData = docService
				.findAllServiceGroupByAddressId();
		List<ServiceDeviceData> serviceDeviceData = docService
				.findServiceByDeviceId();
		List<ServiceVo> services = docService.findAllService();
		mav.addObject("services", services);
		mav.addObject("serviceData", serviceData);
		mav.addObject("serviceAddressData", serviceAddressData);
		mav.addObject("serviceDeviceData", serviceDeviceData);
		return mav;
	}

	@RequestMapping(value = "getPatients/{user_id}", method = RequestMethod.GET)
	public ModelAndView getPatients(@PathVariable("user_id") int id) {
		ModelAndView mav = new ModelAndView("doc/ajax/data_info_patient");
		List<PatientVo> patients = docService.getPatients(id);
		mav.addObject("patients", patients);
		return mav;
	}

	// -----------------设备信息-------------------------//
	@RequestMapping(value = "device_info")
	public ModelAndView device_info() {
		ModelAndView mav = new ModelAndView("doc/ajax/device_info");
		List<TDevice> devices = docService.findAllDevice();
		mav.addObject("devices", devices);
		return mav;
	}

	@RequestMapping(value = "device_add")
	public String device_add() {
		return "doc/ajax/device_add";
	}

	@RequestMapping(value = "device_edit")
	public ModelAndView device_edit(Integer id) {
		ModelAndView mav = new ModelAndView("doc/ajax/device_edit");
		DeviceVo device = docService.findDeviceById(id);
		mav.addObject("device", device);
		return mav;
	}

	@RequestMapping(value = "device_add_json")
	public void device_add_json(TDevice device, HttpServletResponse response)
			throws IOException {
		docService.addDevice(device);
		response.sendRedirect("index#device_info");
	}

	@RequestMapping(value = "device_edit_json")
	public void device_edit_json(TDevice device, HttpServletResponse response)
			throws IOException {
		docService.updateDeviceById(device);
		response.sendRedirect("index#device_info");
	}

	@RequestMapping(value = "device_delete")
	public void device_delete(Integer id, HttpServletResponse response)
			throws IOException {
		docService.deleteDeviceById(id);
		response.sendRedirect("index#device_info");
	}
}
