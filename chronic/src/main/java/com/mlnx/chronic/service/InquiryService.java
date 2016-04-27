package com.mlnx.chronic.service;

import java.util.Map;

public interface InquiryService {

	Map<String, Object> findAllProvince();//所有省

	Map<String, Object> findAllCity(String id);//根据省id查找市

	Map<String, Object> findAllHospitalWithMap(Map<String, Object> map);//查找医院，市id和医院等级

	Map<String, Object> findAllDocWithKey(String keyStr);//关键字查找医生

	Map<String, Object> findAllDocWithHospitalAndGroup(Map<String, Object> map);//根据医院和组查找医生

	Map<String, Object> findAllDocByCity(Map<String, Object> map);//根据疾病和城市查找医生

}
