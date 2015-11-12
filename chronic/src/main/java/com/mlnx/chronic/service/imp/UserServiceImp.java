package com.mlnx.chronic.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mlnx.chronic.repo.PhoneRepository;
import com.mlnx.chronic.entity.Patient;
import com.mlnx.chronic.entity.Phone;
import com.mlnx.chronic.entity.TBloodPressureSetting;
import com.mlnx.chronic.entity.TBloodSugarSetting;
import com.mlnx.chronic.entity.TUser;
import com.mlnx.chronic.entity.TUserExt;
import com.mlnx.chronic.exception.RegisterException;
import com.mlnx.chronic.mapper.TBloodPressureSettingMapper;
import com.mlnx.chronic.mapper.TBloodSugarSettingMapper;
import com.mlnx.chronic.mapper.TUserExtMapper;
import com.mlnx.chronic.mapper.TUserMapper;
import com.mlnx.chronic.repo.PatientRepository;
import com.mlnx.springmvc.service.UserService;
import com.mlnx.springmvc.util.ChronicResponse;
import com.mlnx.springmvc.util.EnumCollection;
import com.mlnx.springmvc.util.PropertiyUtil;

//配置事务，回滚异常可自定义
@Service
@Transactional(rollbackFor=Exception.class)
public class UserServiceImp implements UserService {
	
	@Autowired
	private TUserMapper tUserMapper;
	
	@Autowired
	private TUserExtMapper tUserExtMapper;
	
	@Autowired
	private TBloodPressureSettingMapper tBloodPressureSettingMapper;
	
	@Autowired
	private TBloodSugarSettingMapper tBloodSugarSettingMapper;
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	  private PhoneRepository phoneRespository;
	
	@Transactional(rollbackFor=RegisterException.class)
	@Override
	public ChronicResponse registUser(TUser user) throws Exception {
		//验证手机号是否存在
		TUser tuser = tUserMapper.selectByPhone(user.getPhone());
		if(tuser==null){
			tUserMapper.insert(user);
			int userId = user.getId();
			TBloodPressureSetting tBloodPressureSetting = PropertiyUtil.getDefaultTBloodPressureSetting();
			TBloodSugarSetting tBloodSugarSetting = PropertiyUtil.getDefaultTBloodSugarSetting();
			tBloodPressureSetting.setUserId(userId);
			tBloodSugarSetting.setUserId(userId);
			tBloodPressureSettingMapper.insert(tBloodPressureSetting);
			tBloodSugarSettingMapper.insert(tBloodSugarSetting);
			Patient patient = new Patient(user.getPhone());
			int patientId = patientRepository.save(patient);
			TUserExt userExt = new TUserExt(patientId,userId);
			tUserExtMapper.insert(userExt);
			return new ChronicResponse(EnumCollection.ResponseCode.SUCCESS);
		} else{
			return new ChronicResponse(EnumCollection.ResponseCode.EXIST);
		}
	}

	@Override
	public ChronicResponse login(String phone, String password)
			throws Exception {
		TUser user = tUserMapper.selectByPhone(phone);
		if(user == null){
			return new ChronicResponse(EnumCollection.ResponseCode.LOGIN_USERNAME_NOT_EXIST);
		}
		if(user.getPassword().equals(password)){
			return new ChronicResponse(EnumCollection.ResponseCode.LOGIN_SUCCESS);
		}
		return new ChronicResponse(EnumCollection.ResponseCode.LOGIN_PASSWORD_ERROR);
	}

	@Override
	public ChronicResponse updateBloodPressureSetting(
			TBloodPressureSetting bloodPressureSetting) {
		tBloodPressureSettingMapper.updateByPrimaryKey(bloodPressureSetting);
		return new ChronicResponse(EnumCollection.ResponseCode.UPDATE_BLOODPRESSURESETTING_SUCCESS);
	}

	@Override
	public ChronicResponse updateBloodSugarSetting(
			TBloodSugarSetting bloodSugarSetting) {
		tBloodSugarSettingMapper.updateByPrimaryKey(bloodSugarSetting);
		return new ChronicResponse(EnumCollection.ResponseCode.UPDATE_BLOODSUGARSETTING_SUCCESS);
	}

	@Override
	public ChronicResponse registUser(TUser user, Integer code) {
		//验证手机号是否存在
		TUser tuser = tUserMapper.selectByPhone(user.getPhone());
		if(tuser==null){
			//验证验证码是否输入正确
			Phone ph = phoneRespository.findById(user.getPhone());
			if (code == ph.getCode()) {
				tUserMapper.insert(user);
				int userId = user.getId();
				TBloodPressureSetting tBloodPressureSetting = PropertiyUtil.getDefaultTBloodPressureSetting();
				TBloodSugarSetting tBloodSugarSetting = PropertiyUtil.getDefaultTBloodSugarSetting();
				tBloodPressureSetting.setUserId(userId);
				tBloodSugarSetting.setUserId(userId);
				tBloodPressureSettingMapper.insert(tBloodPressureSetting);
				tBloodSugarSettingMapper.insert(tBloodSugarSetting);
				Patient patient = new Patient(user.getPhone());
				int patientId = patientRepository.save(patient);
				TUserExt userExt = new TUserExt(patientId,userId);
				tUserExtMapper.insert(userExt);
				return new ChronicResponse(EnumCollection.ResponseCode.SUCCESS);
			} else{
				return new ChronicResponse(EnumCollection.ResponseCode.REGISTER_VALIDCODE_ERROR);
			}
			
		} else{
			return new ChronicResponse(EnumCollection.ResponseCode.EXIST);
		}
		
	}

}
