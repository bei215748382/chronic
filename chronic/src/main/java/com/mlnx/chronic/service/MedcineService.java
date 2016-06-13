package com.mlnx.chronic.service;

import java.util.List;
import java.util.Map;

import com.mlnx.chronic.entity.TMedcine;
import com.mlnx.chronic.entity.TPatientDinner;
import com.mlnx.chronic.entity.TPatientMedcine;
import com.mlnx.chronic.util.ChronicResponse;
import com.mlnx.chronic.vo.medcine.TakeMedicineInfo;

public interface MedcineService {

	// 添加药物
	public ChronicResponse regist(TMedcine medcine);

	//根据id删除
	public ChronicResponse delete(int id);
	
	//更新药物内容
	public ChronicResponse update(TMedcine medcine);

	//查找所有药物
	public List<TMedcine> findAll();
	
	//设置用餐时间
	public ChronicResponse settingDinner(TPatientDinner dinner);

	//获取饭前、饭后的药物
	public Map<String, Object> getMedcine(TPatientMedcine tpm);

	//获取药物处方
	public Map<String, Object> getMedcinePrescription(Integer id);

	//获取最近的服药历史
	public Map<String, Object> getMedcineHistory(Integer id, Long time,
			Integer limit);

	//保存服药记录
	public ChronicResponse saveTakeMedcine(TakeMedicineInfo info);

}
