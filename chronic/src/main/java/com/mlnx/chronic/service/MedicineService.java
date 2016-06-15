package com.mlnx.chronic.service;

import java.util.List;
import java.util.Map;

import com.mlnx.chronic.entity.TMedicine;
import com.mlnx.chronic.entity.TPatientDinner;
import com.mlnx.chronic.entity.TPatientMedicine;
import com.mlnx.chronic.util.ChronicResponse;
import com.mlnx.chronic.vo.medicine.TakeMedicineInfo;

public interface MedicineService {

	// 添加药物
	public ChronicResponse regist(TMedicine medicine);

	//根据id删除
	public ChronicResponse delete(int id);
	
	//更新药物内容
	public ChronicResponse update(TMedicine medicine);

	//查找所有药物
	public List<TMedicine> findAll();
	
	//设置用餐时间
	public ChronicResponse settingDinner(TPatientDinner dinner);

	//获取药物处方
	public Map<String, Object> getMedicinePrescription(Integer id);

	//获取最近的服药历史
	public Map<String, Object> getMedicineHistory(Integer id, Long time,
			Integer limit);

	//保存服药记录
	public ChronicResponse saveTakeMedicine(TakeMedicineInfo info);

}
