package com.mlnx.chronic.service.imp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.antlr.grammar.v3.ANTLRv3Parser.throwsSpec_return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.mlnx.chronic.entity.Patient;
import com.mlnx.chronic.entity.TBloodPressureSetting;
import com.mlnx.chronic.entity.TBloodSugarSetting;
import com.mlnx.chronic.entity.TPhoneValid;
import com.mlnx.chronic.entity.TUser;
import com.mlnx.chronic.entity.TUserExt;
import com.mlnx.chronic.entity.TUserFriends;
import com.mlnx.chronic.exception.RegisterException;
import com.mlnx.chronic.exception.TransactionalException;
import com.mlnx.chronic.mapper.TBloodPressureSettingMapper;
import com.mlnx.chronic.mapper.TBloodSugarSettingMapper;
import com.mlnx.chronic.mapper.TPhoneValidMapper;
import com.mlnx.chronic.mapper.TUserExtMapper;
import com.mlnx.chronic.mapper.TUserFriendsMapper;
import com.mlnx.chronic.mapper.TUserMapper;
import com.mlnx.chronic.repo.PatientRepository;
import com.mlnx.chronic.vo.RegistUser;
import com.mlnx.springmvc.service.UserService;
import com.mlnx.springmvc.util.ChronicResponse;
import com.mlnx.springmvc.util.EnumCollection;
import com.mlnx.springmvc.util.PropertiyUtil;
import com.mlnx.springmvc.util.StringUtil;

@Service
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
	private TPhoneValidMapper tPhoneValidMapper;

	@Autowired
	private TUserFriendsMapper tUserFriendsMapper;

	// 配置事务，回滚异常可自定义
	@Transactional(rollbackFor = RegisterException.class)
	@Override
	public ChronicResponse registUser(TUser user) throws Exception {
		try {
			// 验证手机号是否存在
			TUser tuser = tUserMapper.selectByPhone(user.getPhone());
			if (tuser == null) {
				tUserMapper.insert(user);
				int userId = user.getId();
				TBloodPressureSetting tBloodPressureSetting = PropertiyUtil
						.getDefaultTBloodPressureSetting();
				TBloodSugarSetting tBloodSugarSetting = PropertiyUtil
						.getDefaultTBloodSugarSetting();
				tBloodPressureSetting.setUserId(userId);
				tBloodSugarSetting.setUserId(userId);
				tBloodPressureSettingMapper.insert(tBloodPressureSetting);
				tBloodSugarSettingMapper.insert(tBloodSugarSetting);
				Patient patient = new Patient(user.getPhone());
				int patientId = patientRepository.save(patient);
				TUserExt userExt = new TUserExt(patientId, userId);
				tUserExtMapper.insert(userExt);
				return new ChronicResponse(EnumCollection.ResponseCode.SUCCESS);
			} else {
				return new ChronicResponse(EnumCollection.ResponseCode.EXIST);
			}
		} catch (Exception e) {
			throw new RegisterException();
		}
	}

	@Override
	public Map<String, String> login(String phone, String password) {
		Map<String, String> map = new HashMap<String, String>();
		TUser user = tUserMapper.selectByPhone(phone);
		if (user == null) {
			map.put("code",
					EnumCollection.ResponseCode.LOGIN_USERNAME_NOT_EXIST
							.getCode());
			map.put("msg", EnumCollection.ResponseCode.LOGIN_USERNAME_NOT_EXIST
					.getMsg());
			return map;
		}
		if (user.getPassword().equals(password)) {
			map.put("code", EnumCollection.ResponseCode.LOGIN_SUCCESS.getCode());
			map.put("msg", EnumCollection.ResponseCode.LOGIN_SUCCESS.getMsg());
			map.put("id", user.getId() + "");
			return map;
		} else {
			map.put("code",
					EnumCollection.ResponseCode.LOGIN_PASSWORD_ERROR.getCode());
			map.put("msg",
					EnumCollection.ResponseCode.LOGIN_PASSWORD_ERROR.getMsg());
			return map;
		}
	}

	@Override
	public ChronicResponse updateBloodPressureSetting(
			TBloodPressureSetting bloodPressureSetting) {
		tBloodPressureSettingMapper.updateByPrimaryKey(bloodPressureSetting);
		return new ChronicResponse(
				EnumCollection.ResponseCode.UPDATE_BLOODPRESSURESETTING_SUCCESS);
	}

	@Override
	public ChronicResponse updateBloodSugarSetting(
			TBloodSugarSetting bloodSugarSetting) {
		tBloodSugarSettingMapper.updateByPrimaryKey(bloodSugarSetting);
		return new ChronicResponse(
				EnumCollection.ResponseCode.UPDATE_BLOODSUGARSETTING_SUCCESS);
	}

	@Transactional(rollbackFor = RegisterException.class)
	@Override
	public ChronicResponse registUser(RegistUser u) throws Exception {
		try {
			// 验证手机号是否存在
			TUser tuser = tUserMapper.selectByPhone(u.getPhone());
			if (tuser == null) {
				// TODO 验证验证码是否输入正确
				TPhoneValid ph = tPhoneValidMapper.selectByPhone(u.getPhone());
				if (ph != null && u.getCode().equals(ph.getValidcode())) {
					tuser = new TUser();
					tuser.setPhone(u.getPhone());
					tuser.setPassword(u.getPassword());
					tUserMapper.insert(tuser);
					int userId = tuser.getId();
					TBloodPressureSetting tBloodPressureSetting = PropertiyUtil
							.getDefaultTBloodPressureSetting();
					TBloodSugarSetting tBloodSugarSetting = PropertiyUtil
							.getDefaultTBloodSugarSetting();
					tBloodPressureSetting.setUserId(userId);
					tBloodSugarSetting.setUserId(userId);
					tBloodPressureSettingMapper.insert(tBloodPressureSetting);
					tBloodSugarSettingMapper.insert(tBloodSugarSetting);
					Patient patient = new Patient(u.getPhone());
					int patientId = patientRepository.save(patient);
					TUserExt userExt = new TUserExt(patientId, userId);
					tUserExtMapper.insert(userExt);
					return new ChronicResponse(
							EnumCollection.ResponseCode.SUCCESS);
				} else {
					return new ChronicResponse(
							EnumCollection.ResponseCode.REGISTER_VALIDCODE_ERROR);
				}
			} else {
				return new ChronicResponse(EnumCollection.ResponseCode.EXIST);
			}
		} catch (Exception e) {
			throw new RegisterException();
		}

	}

	@Override
	public ChronicResponse addFriend(TUserFriends tUserFriends) {
		// 判断用户是否已发送添加请求
		TUserFriends result = tUserFriendsMapper
				.selectByUserFriends(tUserFriends);
		if (result == null) {
			tUserFriends.setConfirm(0);// 0表示用户只是发起添加好友请求
			if(tUserFriends.getGroupId()==1){//用户组是家庭组的话，默认设置公开，其他组不公开
				tUserFriends.setOpen(1);	
			} else {
				tUserFriends.setOpen(0);	
			}
			tUserFriendsMapper.insert(tUserFriends);
			return new ChronicResponse(
					EnumCollection.ResponseCode.USER_ADD_FRIEND_REQUEST);
		} else {
			return new ChronicResponse(
					EnumCollection.ResponseCode.USER_ADD_FRIEND_REQUEST_FAIL);
		}
	}

	@Override
	public List<TUserFriends> getFriendsByIdAndGroupId(int id, int groupId) {
		TUserFriends tUserFriends = new TUserFriends(id, groupId);
		return tUserFriendsMapper.selectByIdAndGroupId(tUserFriends);
	}

	@Override
	public List<TUserFriends> confirmFriendList(int userId) {
		TUserFriends tUserFriends = new TUserFriends();
		tUserFriends.setFriendId(userId);
		tUserFriends.setConfirm(0);
		return tUserFriendsMapper.selectByUserIdAndUNConfirmed(tUserFriends);
	}

	@Transactional(rollbackFor = TransactionalException.class)
	@Override
	public ChronicResponse confirmAndCancel(TUserFriends tUserFriends,
			int confirm) throws Exception {
		try {
			TUserFriends u = tUserFriendsMapper.selectByPrimaryKey(tUserFriends.getId());
			if(u.getConfirm()==1){
				return new ChronicResponse(
						EnumCollection.ResponseCode.ADD_FRIENDS_SUCCESS_ALREADY);
			}
			if (confirm == 1) {// 用户同意的话,用户请求的好友关系生效
				tUserFriends.setConfirm(confirm);
				tUserFriendsMapper.updateByPrimaryKey(tUserFriends);
				// 同时添加对方为好友
				toBeFriend(u);
				return new ChronicResponse(
						EnumCollection.ResponseCode.ADD_FRIENDS_SUCCESS);
			} else if (confirm == 0) { // 用户不同意就删除这条请求记录
				tUserFriendsMapper.deleteByPrimaryKey(tUserFriends.getId());
				return new ChronicResponse(
						EnumCollection.ResponseCode.ADD_FRIENDS_CANCEL);
			}
		} catch (Exception e) {
			throw new TransactionalException(StringUtil.friendConfirmerror);
		}
		return new ChronicResponse(
				EnumCollection.ResponseCode.ADD_FRIENDS_ERROR);

	}

	private void toBeFriend(TUserFriends u) {
		TUserFriends uu= new TUserFriends();
		uu.setFriendId(u.getUserId());
		uu.setUserId(u.getFriendId());
		uu.setGroupId(u.getGroupId());
		uu.setFriendRemark(u.getRemark());
		uu.setFouce(1);
		uu.setOpen(0);
		uu.setConfirm(1);
		tUserFriendsMapper.insert(uu);

	}
	
	@Override
	public ChronicResponse modifyFriendMark(TUserFriends tUserFriends) {
		tUserFriendsMapper.updateFriendRemark(tUserFriends);
		return new ChronicResponse(
				EnumCollection.ResponseCode.UPDATE_FRIEND_REMARK_SUCCESS);
	}

	@Override
	public Map<String, Object> getCode(String phone) {
		HashMap<String, Object> result = null;
		// 初始化SDK
		CCPRestSmsSDK restAPI = new CCPRestSmsSDK();

		// ******************************注释*********************************************
		// *初始化服务器地址和端口 *
		// *沙盒环境（用于应用开发调试）：restAPI.init("sandboxapp.cloopen.com", "8883");*
		// *生产环境（用户应用上线使用）：restAPI.init("app.cloopen.com", "8883"); *
		// *******************************************************************************
		restAPI.init("sandboxapp.cloopen.com", "8883");

		// ******************************注释*********************************************
		// *初始化主帐号和主帐号令牌,对应官网开发者主账号下的ACCOUNT SID和AUTH TOKEN *
		// *ACOUNT SID和AUTH TOKEN在登陆官网后，在“应用-管理控制台”中查看开发者主账号获取*
		// *参数顺序：第一个参数是ACOUNT SID，第二个参数是AUTH TOKEN。 *
		// *******************************************************************************
		restAPI.setAccount(StringUtil.accountSid, StringUtil.accountToken);

		// ******************************注释*********************************************
		// *初始化应用ID *
		// *测试开发可使用“测试Demo”的APP ID，正式上线需要使用自己创建的应用的App ID *
		// *应用ID的获取：登陆官网，在“应用-应用列表”，点击应用名称，看应用详情获取APP ID*
		// *******************************************************************************
		restAPI.setAppId(StringUtil.appId);

		// ******************************注释****************************************************************
		// *调用发送模板短信的接口发送短信 *
		// *参数顺序说明： *
		// *第一个参数:是要发送的手机号码，可以用逗号分隔，一次最多支持100个手机号 *
		// *第二个参数:是模板ID，在平台上创建的短信模板的ID值；测试的时候可以使用系统的默认模板，id为1。 *
		// *系统默认模板的内容为“【云通讯】您使用的是云通讯短信模板，您的验证码是{1}，请于{2}分钟内正确输入”*
		// *第三个参数是要替换的内容数组。 *
		// **************************************************************************************************

		// **************************************举例说明***********************************************************************
		// *假设您用测试Demo的APP ID，则需使用默认模板ID 1，发送手机号是13800000000，传入参数为6532和5，则调用方式为
		// *
		// *result = restAPI.sendTemplateSMS("13800000000","1" ,new
		// String[]{"6532","5"}); *
		// *则13800000000手机号收到的短信内容是：【云通讯】您使用的是云通讯短信模板，您的验证码是6532，请于5分钟内正确输入 *
		// *********************************************************************************************************************
		int code = (int) (Math.random() * 1000000);
		result = restAPI.sendTemplateSMS(phone, "1",
				new String[] { String.valueOf(code), "1" });

		if ("000000".equals(result.get("statusCode"))) {
			// 正常返回输出data包体信息（map）
			HashMap<String, Object> data = (HashMap<String, Object>) result
					.get("data");
			Set<String> keySet = data.keySet();
			for (String key : keySet) {
				Object object = data.get(key);
				System.out.println(key + " = " + object);
			}
		} else {
			// 异常返回输出错误码和错误信息
			System.out.println("错误码=" + result.get("statusCode") + " 错误信息= "
					+ result.get("statusMsg"));
			return result;
		}
		TPhoneValid ph = tPhoneValidMapper.selectByPhone(phone);
		if (ph != null) {
			ph.setDate(new Date());
			ph.setValidcode(code);
			tPhoneValidMapper.updateByPrimaryKey(ph);
		} else {
			ph = new TPhoneValid();
			ph.setPhone(phone);
			ph.setValidcode(code);
			ph.setDate(new Date());
			tPhoneValidMapper.insert(ph);
		}
		return result;
	}

	/**
	 * 纯修改手机密码
	 */
	@Override
	public ChronicResponse modifyPassword(TUser user) {
		if (user.getPhone() != null) {
			user.setPhone(null);
		}
		tUserMapper.updateByPrimaryKey(user);
		return new ChronicResponse(
				EnumCollection.ResponseCode.UPDATE_USER_PASSWORD_SUCCESS);
	}

	/**
	 * 查看是否有权限
	 */
	@Override
	public ChronicResponse havePermission(TUserFriends tUserFriends) {
		TUserFriends tuf = tUserFriendsMapper.findByUserIdAndFriendId(tUserFriends);
		if(tuf.getOpen()==0){
			return new ChronicResponse(EnumCollection.ResponseCode.NO_PERMISSION_OPEN);
		} else if(tuf.getOpen()==1){
			return new ChronicResponse(EnumCollection.ResponseCode.PERMISSION_OPEN_ALLOWED);
		}
		return null;//
	}

}
