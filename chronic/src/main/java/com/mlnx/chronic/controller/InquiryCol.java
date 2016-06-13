package com.mlnx.chronic.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mlnx.chronic.entity.TQuestion;
import com.mlnx.chronic.service.InquiryService;
import com.mlnx.chronic.util.ChronicResponse;
import com.mlnx.chronic.util.FileUtils;
import com.mlnx.chronic.vo.UsrBook;

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
	public Map<String, Object> findAllDocWithKey(
			@PathParam("keyStr") String keyStr) {
		return inquiryService.findAllDocWithKey(keyStr);
	}

	/**
	 * 根据城市
	 * 
	 * @return
	 */
	@RequestMapping(value = "all/doc/by/city")
	@ResponseBody
	public Map<String, Object> findAllDocByCity(
			@PathParam("cityId") String cityId,
			@PathParam("disease") String disease) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cityId", cityId);
		map.put("disease", disease);
		return inquiryService.findAllDocByCity(map);
	}

	/**
	 * 发起提问
	 * 
	 * @param question
	 * @return
	 */
	@RequestMapping(value = "question")
	@ResponseBody
	public ChronicResponse question(@RequestBody TQuestion question) {
		return inquiryService.question(question);
	}

	/**
	 * 咨询历史
	 * 
	 * @param keyword
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "history/question")
	@ResponseBody
	public Map<String, Object> historyQuestion(
			@RequestParam(value = "keyword", required = false) String keyword,@RequestParam(value = "start", defaultValue = "0") Integer start,
			@RequestParam(value = "end", defaultValue = "10") Integer end) {
		return inquiryService.historyQuestion(keyword, start,end);
	}

	@RequestMapping(value = "doc/history/question")
	@ResponseBody
	public Map<String, Object> docHistoryQuestion(
			@RequestParam(value = "id", required = false) String id,@RequestParam(value = "start", defaultValue = "0") Integer start,
			@RequestParam(value = "end", defaultValue = "10") Integer end) {
		return inquiryService.docHistoryQuestion(id, start,end);
	}
	
	@RequestMapping(value = "book")
	@ResponseBody
	public ChronicResponse book(UsrBook usrBook,HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException {
		List<String> str = FileUtils.uploadFiles(request, response);
		usrBook.setPics(str);
		return inquiryService.book(usrBook);
	}
	
	@RequestMapping(value = "fileUpload")
	public String index(){
		return "fileUpload";
	}
	
}
