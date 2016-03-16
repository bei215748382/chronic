package com.mlnx.chronic.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mlnx.chronic.entity.TGroup;
import com.mlnx.chronic.entity.TGroupPatient;
import com.mlnx.chronic.entity.TIdentity;
import com.mlnx.chronic.exception.RegisterException;
import com.mlnx.chronic.service.GroupService;
import com.mlnx.chronic.service.PatientService;
import com.mlnx.chronic.util.ChronicResponse;

@Controller
@RequestMapping(value = "/patient")
public class PatientCol {

	@Autowired
	private GroupService groupService;

	@Autowired
	private PatientService patientService;

	@RequestMapping(value = "add/group", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addGroup(
			@RequestHeader("userId") Integer userId,
			@RequestParam("name") String name) {
		TGroup group = new TGroup();
		group.setName(name);
		group.setUserId(userId);
		return groupService.regist(group);
	}

	@RequestMapping(value = "edit/group", method = RequestMethod.POST)
	@ResponseBody
	public ChronicResponse editGroup(@RequestHeader("groupId") Integer groupId,
			@RequestParam("name") String name) {
		TGroup group = new TGroup();
		group.setId(groupId);
		group.setName(name);
		return groupService.edit(group);
	}

	@RequestMapping(value = "delete/group", method = RequestMethod.POST)
	@ResponseBody
	public ChronicResponse deleteGroup(@RequestHeader("id") Integer id) {
		return groupService.delete(id);
	}

	@RequestMapping(value = "add/patient", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addPatient(
			@RequestHeader("identity") String name) throws RegisterException {
		TIdentity identity = new TIdentity();
		identity.setName(name);
		return patientService.regist(identity);
	}
	
	@RequestMapping(value = "add/patient/group", method = RequestMethod.POST)
	@ResponseBody
	public ChronicResponse addPatientGroup(
			@RequestHeader("groupId") Integer groupId,
			@RequestHeader("patientId") Integer patientId) {
		TGroupPatient groupPatient = new TGroupPatient();
		groupPatient.setGroupId(groupId);
		groupPatient.setPatientId(patientId);
		return patientService.addToGroup(groupPatient);
	}
	
	@RequestMapping(value = "edit/patient/group", method = RequestMethod.POST)
	@ResponseBody
	public ChronicResponse editPatientGroup(
			@RequestHeader("groupId") Integer groupId,
			@RequestHeader("patientId") Integer patientId) {
		TGroupPatient groupPatient = new TGroupPatient();
		groupPatient.setGroupId(groupId);
		groupPatient.setPatientId(patientId);
		return patientService.edit(groupPatient);
	}
	
	@RequestMapping(value = "delete/patient/group", method = RequestMethod.POST)
	@ResponseBody
	public ChronicResponse deletePatientGroup(
			@RequestHeader("groupId") Integer id) {
		return patientService.delete(id);
	}
	
	@RequestMapping(value = "search/group/patient", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> searchGroupPatient(
			@RequestHeader("groupId") Integer groupId) {
		return patientService.findGroupPatients(groupId);
	}
	
	//获取所有组
	@RequestMapping(value = "search/group", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> searchGroup(@RequestHeader("uid") Integer id) {
		return groupService.searchGroup(id);
	}
}
