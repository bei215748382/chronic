package com.mlnx.springmvc.service;

import com.mlnx.chronic.entity.TBloodPressureSetting;
import com.mlnx.chronic.entity.TBloodSugarSetting;
import com.mlnx.chronic.entity.TUser;
import com.mlnx.springmvc.util.ChronicResponse;

public interface UserService {

	// 注册用户
	public ChronicResponse registUser(TUser user) throws Exception;

	// 登入
	public ChronicResponse login(String phone, String password)
			throws Exception;

	// 修改血压阀值
	public ChronicResponse updateBloodPressureSetting(
			TBloodPressureSetting bloodPressureSetting);

	//修改血糖阀值
	public ChronicResponse updateBloodSugarSetting(
			TBloodSugarSetting bloodSugarSetting);

	//手机验证码注册用户
	public ChronicResponse registUser(TUser user, Integer code);
}
