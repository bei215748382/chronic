package com.mlnx.chronic.service;

import java.util.Map;

import com.mlnx.chronic.entity.TGroupPatient;
import com.mlnx.chronic.entity.TIdentity;
import com.mlnx.chronic.exception.RegisterException;
import com.mlnx.chronic.util.ChronicResponse;

public interface PatientService {

	// 添加病人
	public Map<String, Object> regist(TIdentity identity) throws RegisterException;

	// 添加病人到群组
	public ChronicResponse addToGroup(TGroupPatient groupPatient);

	// 移动病人到其他组
	public ChronicResponse edit(TGroupPatient groupPatient);

	// 从群组中移除病人
	public ChronicResponse delete(int id);

	// 获取组下所有病人
	public Map<String, Object> findGroupPatients(int gid);

}
