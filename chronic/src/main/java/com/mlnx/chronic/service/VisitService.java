package com.mlnx.chronic.service;

import java.util.List;
import java.util.Map;

import com.mlnx.chronic.entity.TVisit;
import com.mlnx.chronic.exception.TransactionalException;
import com.mlnx.chronic.util.ChronicResponse;
import com.mlnx.chronic.vo.VisitExt;

public interface VisitService {

	// 预约回访
	public ChronicResponse regist(TVisit visit, List<Integer> tests)
			throws TransactionalException;

	// 编辑回访，修改检测项等
	public ChronicResponse edit(VisitExt visitExt)
			throws TransactionalException;

	//查看历史回访
	public Map<String, Object> search(Integer doctorId);
	
	// 编辑回访
	public ChronicResponse edit(TVisit visit);

	// 删除回访
	public ChronicResponse delete(int id);

	// 回访记录，根据医生id查找
	public Map<String, Object> findAllByDoctorId(int id);

}
