package com.mlnx.chronic.service;

import java.util.Map;

import com.mlnx.chronic.entity.TQuestion;
import com.mlnx.chronic.util.ChronicResponse;
import com.mlnx.chronic.vo.UsrBook;

public interface InquiryService {

	Map<String, Object> findAllProvince();// 所有省

	Map<String, Object> findAllCity(String id);// 根据省id查找市

	Map<String, Object> findAllHospitalWithMap(Map<String, Object> map);// 查找医院，市id和医院等级

	Map<String, Object> findAllDocWithKey(String keyStr);// 关键字查找医生

	Map<String, Object> findAllDocWithHospitalAndGroup(Map<String, Object> map);// 根据医院和组查找医生

	Map<String, Object> findAllDocByCity(Map<String, Object> map);// 根据疾病和城市查找医生

	ChronicResponse question(TQuestion question);// 提问

	Map<String, Object> historyQuestion(String keyword, Integer start,
			Integer end);// 问题历史

	Map<String, Object> docHistoryQuestion(String id, Integer start, Integer end);// 医生咨询历史

	ChronicResponse book(UsrBook usrBook);//发送预约

}
