package com.mlnx.chronic.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mlnx.chronic.entity.TMedcine;
import com.mlnx.chronic.entity.TPatientDinner;
import com.mlnx.chronic.entity.TPatientMedcine;
import com.mlnx.chronic.mapper.TMedcineMapper;
import com.mlnx.chronic.mapper.TPatientDinnerMapper;
import com.mlnx.chronic.mapper.TPatientMedcineMapper;
import com.mlnx.chronic.service.MedcineService;
import com.mlnx.chronic.util.ChronicResponse;
import com.mlnx.chronic.util.EnumCollection.ResponseCode;
import com.mlnx.chronic.util.StringUtil;
import com.mlnx.chronic.vo.Prescription;

@Transactional
@Service
public class MedcineServiceImpl implements MedcineService {

	@Autowired
	private TMedcineMapper tMedcineMapper;
	
	@Autowired
	private TPatientDinnerMapper tPatientDinnerMapper;
	
	@Autowired
	private TPatientMedcineMapper tPatientMedcineMapper;

	@Override
	public ChronicResponse regist(TMedcine medcine) {
		try {
			tMedcineMapper.insert(medcine);
			return new ChronicResponse(ResponseCode.ADD_MEDCINE_SUCCESS);
		} catch (Exception e) {
			return new ChronicResponse(ResponseCode.ADD_MEDCINE_ERROR);
		} 
	}

	@Override
	public ChronicResponse delete(int id) {
		try {
			tMedcineMapper.deleteByPrimaryKey(id);
			return new ChronicResponse(ResponseCode.DELETE_MEDCINE_SUCCESS);
		} catch (Exception e) {
			return new ChronicResponse(ResponseCode.DELETE_MEDCINE_ERROR);
		} 
	}

	@Override
	public ChronicResponse update(TMedcine medcine) {
		try {
			tMedcineMapper.updateByPrimaryKey(medcine);
			return new ChronicResponse(ResponseCode.UPDATE_MEDCINE_SUCCESS);
		} catch (Exception e) {
			return new ChronicResponse(ResponseCode.UPDATE_MEDCINE_ERROR);
		} 
	}

	@Override
	public List<TMedcine> findAll() {
		return tMedcineMapper.selectAll();
	}

	@Override
	public ChronicResponse settingDinner(TPatientDinner dinner) {
		try {
			tPatientDinnerMapper.insert(dinner);
			return new ChronicResponse(ResponseCode.SETTING_DINNER_SUCCESS);
		} catch (Exception e) {
			return new ChronicResponse(ResponseCode.SETTING_DINNER_ERROR);
		} 
	}

	@Override
	public Map<String, Object> getMedcine(TPatientMedcine tpm) {
		Map<String,Object> map = new HashMap<String, Object>();
		List<Prescription>  list = tPatientMedcineMapper.getMedcine(tpm);
		map.put(StringUtil.responseObjList,list);
		return map;
	}

}
