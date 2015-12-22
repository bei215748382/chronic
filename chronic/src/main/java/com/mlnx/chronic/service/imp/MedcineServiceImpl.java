package com.mlnx.chronic.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mlnx.chronic.entity.TMedcine;
import com.mlnx.chronic.mapper.TMedcineMapper;
import com.mlnx.springmvc.service.MedcineService;
import com.mlnx.springmvc.util.ChronicResponse;
import com.mlnx.springmvc.util.EnumCollection.ResponseCode;

@Transactional
@Service
public class MedcineServiceImpl implements MedcineService {

	@Autowired
	private TMedcineMapper tMedcineMapper;

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

}
