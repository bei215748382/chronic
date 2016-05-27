package com.mlnx.chronic.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mlnx.chronic.entity.TPatientBloodPressure;
import com.mlnx.chronic.entity.TPatientBloodSugar;
import com.mlnx.chronic.service.BloodPressureService;
import com.mlnx.chronic.service.BloodSugarService;
import com.mlnx.chronic.util.ChronicResponse;

@Controller
@RequestMapping(value = "/measure")
public class MeasureCol {

	private static final Logger log = LoggerFactory.getLogger(MeasureCol.class);

	@Autowired
	private BloodPressureService bloodPressureService;

	@Autowired
	private BloodSugarService bloodSugarService;

	@RequestMapping(value = "search/last/bloodSugar/{patientId:[0-9]*}/limit/{limit:[0-9]*}/{date:[0-9]*}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> searchBloodSugarWithEndAndStateAndLimit(
			@PathVariable("patientId") Integer patientId,
			@PathVariable("limit") Integer limit,
			@PathVariable("date") Long date,
			@RequestParam(value="state",required=false) String state) {
		return bloodSugarService.searchLastBloodSugar(patientId, date, limit,
				state);
	}

	@RequestMapping(value = "get/first/bloodPressure/{patientId:[0-9]*}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getFirstBloodPressure(
			@PathVariable("patientId") Integer patientId) {
		return bloodPressureService.findByPatientIdLimitOne(patientId);
	}

	@RequestMapping(value = "get/bloodPressure/count/{patientId:[0-9]*}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getBloodPressureCount(
			@PathVariable("patientId") Integer patientId) {
		return bloodPressureService.findCountByPatientId(patientId);
	}

	@RequestMapping(value = "search/first/bloodSugar/{patientId:[0-9]*}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> searchFirstBloodSugar(
			@PathVariable("patientId") Integer patientId) {
		return bloodSugarService.findByPatientIdLimitOne(patientId);
	}

	@RequestMapping(value = "search/bloodSugar/count/{patientId:[0-9]*}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> searchBloodSugarCount(
			@PathVariable("patientId") Integer patientId) {
		return bloodSugarService.findCountByPatientId(patientId);
	}

	@RequestMapping(value = "add/bloodPressure", method = RequestMethod.POST)
	@ResponseBody
	public ChronicResponse addBloodPressure(@RequestBody TPatientBloodPressure bloodPressure) {
		return bloodPressureService.addBloodPressure(bloodPressure);
	}

	@RequestMapping(value = "get/bloodPressure/{startTime:[0-9]*}/{endTime:[0-9]*}/{id:[0-9]*}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getBloodPressureWithTimeRange(
			@PathVariable("startTime") Long startTime,
			@PathVariable("endTime") Long endTime,
			@PathVariable("id") Integer id) {
		return bloodPressureService.getBloodPressureByPatientIdWithTimeRange(
				startTime, endTime, id);
	}

	@RequestMapping(value = "get/bloodPressure/{endTime:[0-9]*}/{id:[0-9]*}/limit/{limit:[0-9]*}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getBloodPressuereWithEndAndLimit(
			@PathVariable("limit") Integer limit,
			@PathVariable("endTime") Long endTime,
			@PathVariable("id") Integer id) {
		return bloodPressureService.getBloodPressureWithEndAndLimit(limit,
				endTime, id);
	}

	@RequestMapping(value = "save/bloodSugar", method = RequestMethod.POST)
	@ResponseBody
	public ChronicResponse saveBloodSugar(@RequestBody TPatientBloodSugar bloodSugar) {
		return bloodSugarService.addBloodSugar(bloodSugar);
	}

	@RequestMapping(value = "search/bloodSugar/{startTime:[0-9]*}/{endTime:[0-9]*}/{id:[0-9]*}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> searchBloodSugarWithTimeRange(
			@PathVariable("startTime") Long startTime,
			@PathVariable("endTime") Long endTime,
			@PathVariable("id") Integer id) {
		return bloodSugarService.searchBloodSugarWithTimeRange(startTime,
				endTime, id);
	}

	@RequestMapping(value = "syn/bloodPressure", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> synBloodPressureWithTimeRange(
			@RequestParam(value="startTime",required=false) Long startTime,
			@RequestParam(value="endTime",required=false) Long endTime,
			@RequestParam(value="timestamp",required=false) Long timestamp,
			@RequestParam(value="id",required=false) Integer id) {
		return bloodPressureService.synBloodPressureWithTimeRange(startTime,
				endTime, id, timestamp);
	}

	@RequestMapping(value = "syn/bloodSugar", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> synBloodSugarWithTimeRange(
			@RequestParam(value="startTime",required=false) Long startTime,
			@RequestParam(value="endTime",required=false) Long endTime,
			@RequestParam(value="timestamp",required=false) Long timestamp,
			@RequestParam(value="id",required=false) Integer id) {
		return bloodSugarService.synBloodSugarWithTimeRange(startTime, endTime,
				id, timestamp);
	}
	
	@RequestMapping(value = "search/bloodSugar/month")
	@ResponseBody
	public Map<String, Object> searchBloodSugarMonth(@RequestParam(value="patientId",required=true) Integer patientId) {
		return bloodSugarService.getBloodSugarMonth(patientId);
	}
	
	@RequestMapping(value = "search/bloodSugar/date")
	@ResponseBody
	public Map<String, Object> searchBloodSugarDate(@RequestParam(value="patientId",required=true) Integer patientId,@RequestParam(value="date",required=true) String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date start = sdf.parse(date);
		return bloodSugarService.getBloodSugarDate(patientId,start);
	}
	
	@RequestMapping(value = "get/bloodPressure/month")
	@ResponseBody
	public Map<String, Object> getBloodPressureMonth(@RequestParam(value="patientId",required=true) Integer patientId) {
		return bloodPressureService.getBloodPressureMonth(patientId);
	}
	
	@RequestMapping(value = "get/bloodPressure/date")
	@ResponseBody
	public Map<String, Object> getBloodPressureDate(@RequestParam(value="patientId",required=true) Integer patientId,@RequestParam(value="date",required=true) String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date start = sdf.parse(date);
		return bloodPressureService.getBloodPressureDate(patientId,start);
	}
}
