package com.mlnx.chronic.service.imp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mlnx.chronic.entity.Patient;
import com.mlnx.chronic.entity.Patient.Gender;
import com.mlnx.chronic.entity.TDocPatient;
import com.mlnx.chronic.entity.TGroup;
import com.mlnx.chronic.entity.TGroupPatient;
import com.mlnx.chronic.entity.TIdentity;
import com.mlnx.chronic.exception.RegisterException;
import com.mlnx.chronic.exception.TransactionalException;
import com.mlnx.chronic.mapper.TDocPatientMapper;
import com.mlnx.chronic.mapper.TGroupMapper;
import com.mlnx.chronic.mapper.TGroupPatientMapper;
import com.mlnx.chronic.mapper.TIdentityMapper;
import com.mlnx.chronic.repo.PatientRepository;
import com.mlnx.chronic.service.PatientService;
import com.mlnx.chronic.util.ChronicResponse;
import com.mlnx.chronic.util.StringUtil;
import com.mlnx.chronic.util.EnumCollection.ResponseCode;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private TIdentityMapper tIdentityMapper;

	@Autowired
	private TGroupPatientMapper tGroupPatientMapper;

	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private TGroupMapper tGroupMapper;
	
	@Autowired
	private TDocPatientMapper tDocPatientMapper;

	@Transactional
	@Override
	public Map<String, Object> regist(TIdentity identity)
			throws RegisterException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Patient patient = new Patient();
			patient.setName(identity.getName());
			patient.setIdentification(identity.getName());
			patient.setBirthday(new Date());
			patient.setGender(Gender.MALE);
			int patientId = patientRepository.save(patient);
			identity.setPatientId(patientId);
			tIdentityMapper.insert(identity);
			map.put(StringUtil.responseCode,
					ResponseCode.ADD_IDENTITY_SUCCESS.getCode());
			map.put(StringUtil.responseMsg,
					ResponseCode.ADD_IDENTITY_SUCCESS.getMsg());
			map.put(StringUtil.responseObj, patientId);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RegisterException(e);
		}
	}

	@Transactional(rollbackFor=TransactionalException.class)
	@Override
	public ChronicResponse addToGroup(TGroupPatient groupPatient) {
		try {
			Integer id = tGroupPatientMapper.isExist(groupPatient);
			if (id != null && id > 0) {// 病人已存在
				return new ChronicResponse(ResponseCode.ADD_GROUP_PATIENT_FAIL);
			} else {
				TGroup group = tGroupMapper.selectByPrimaryKey(groupPatient.getGroupId());
				TDocPatient dp = new TDocPatient();
				dp.setDocId(group.getUserId());
				dp.setPatientId(groupPatient.getPatientId());
				tDocPatientMapper.insert(dp);
				tGroupPatientMapper.insert(groupPatient);
				return new ChronicResponse(
						ResponseCode.ADD_GROUP_PATIENT_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ChronicResponse(ResponseCode.ADD_GROUP_PATIENT_ERROR);
		}
	}

	@Override
	public ChronicResponse edit(TGroupPatient groupPatient) {
		try {
			tGroupPatientMapper.updateByPrimaryKey(groupPatient);
			return new ChronicResponse(
					ResponseCode.UPDATE_GROUP_PATIENT_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return new ChronicResponse(ResponseCode.UPDATE_GROUP_PATIENT_ERROR);
		}
	}

	@Override
	public ChronicResponse delete(int id) {
		try {
			tGroupPatientMapper.deleteByPrimaryKey(id);
			return new ChronicResponse(
					ResponseCode.DELETE_GROUP_PATIENT_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return new ChronicResponse(ResponseCode.DELETE_GROUP_PATIENT_ERROR);
		}
	}

	@Override
	public Map<String, Object> findGroupPatients(int gid) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<TGroupPatient> names = tGroupPatientMapper
					.findGroupPatients(gid);
			map.put(StringUtil.responseCode,
					ResponseCode.SEARCH_GROUP_PATIENT_SUCCESS.getCode());
			map.put(StringUtil.responseMsg,
					ResponseCode.SEARCH_GROUP_PATIENT_SUCCESS.getMsg());
			map.put(StringUtil.responseObjList, names);
		} catch (Exception e) {
			e.printStackTrace();
			map.put(StringUtil.responseCode,
					ResponseCode.SEARCH_GROUP_PATIENT_ERROR.getCode());
			map.put(StringUtil.responseMsg,
					ResponseCode.SEARCH_GROUP_PATIENT_SUCCESS.getMsg());
		}
		return map;
	}

}
