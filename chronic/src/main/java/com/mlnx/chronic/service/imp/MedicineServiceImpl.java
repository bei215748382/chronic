package com.mlnx.chronic.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mlnx.chronic.entity.TMedicine;
import com.mlnx.chronic.entity.TPatientDinner;
import com.mlnx.chronic.mapper.TMedicineMapper;
import com.mlnx.chronic.mapper.TPatientDinnerMapper;
import com.mlnx.chronic.mapper.TPatientMedicineMapper;
import com.mlnx.chronic.service.MedicineService;
import com.mlnx.chronic.util.ChronicResponse;
import com.mlnx.chronic.util.EnumCollection.ResponseCode;
import com.mlnx.chronic.util.StringUtil;
import com.mlnx.chronic.vo.medicine.MedicinePrescription;
import com.mlnx.chronic.vo.medicine.TakeMedicineInfo;

@Transactional
@Service
public class MedicineServiceImpl implements MedicineService {

	@Autowired
	private TMedicineMapper tMedicineMapper;
	
	@Autowired
	private TPatientDinnerMapper tPatientDinnerMapper;
	
	@Autowired
	private TPatientMedicineMapper tPatientMedicineMapper;

	@Override
	public ChronicResponse regist(TMedicine medicine) {
		try {
			tMedicineMapper.insert(medicine);
			return new ChronicResponse(ResponseCode.ADD_MEDICINE_SUCCESS);
		} catch (Exception e) {
			return new ChronicResponse(ResponseCode.ADD_MEDICINE_ERROR);
		} 
	}

	@Override
	public ChronicResponse delete(int id) {
		try {
			tMedicineMapper.deleteByPrimaryKey(id);
			return new ChronicResponse(ResponseCode.DELETE_MEDICINE_SUCCESS);
		} catch (Exception e) {
			return new ChronicResponse(ResponseCode.DELETE_MEDICINE_ERROR);
		} 
	}

	@Override
	public ChronicResponse update(TMedicine medicine) {
		try {
			tMedicineMapper.updateByPrimaryKey(medicine);
			return new ChronicResponse(ResponseCode.UPDATE_MEDICINE_SUCCESS);
		} catch (Exception e) {
			return new ChronicResponse(ResponseCode.UPDATE_MEDICINE_ERROR);
		} 
	}

	@Override
	public List<TMedicine> findAll() {
		return tMedicineMapper.selectAll();
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
	public Map<String, Object> getMedicinePrescription(Integer id) {
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			List<MedicinePrescription>  list = tPatientMedicineMapper.getMedicinePrescription(id);
			map.put(StringUtil.responseObjList,list);
		} catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public Map<String, Object> getMedicineHistory(Integer id, Long time,
			Integer limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChronicResponse saveTakeMedicine(TakeMedicineInfo info) {
		// TODO Auto-generated method stub
		return null;
	}

}
