package com.mlnx.chronic.service.imp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.mlnx.chronic.entity.Patient;
import com.mlnx.chronic.entity.Patient.Gender;
import com.mlnx.chronic.entity.TBloodPressureSetting;
import com.mlnx.chronic.entity.TBloodSugarSetting;
import com.mlnx.chronic.entity.TGroup;
import com.mlnx.chronic.entity.TGroupPatient;
import com.mlnx.chronic.entity.TPhoneValid;
import com.mlnx.chronic.entity.TUser;
import com.mlnx.chronic.entity.TUserDoc;
import com.mlnx.chronic.entity.TUserExt;
import com.mlnx.chronic.entity.TUserFriends;
import com.mlnx.chronic.exception.RegisterException;
import com.mlnx.chronic.exception.TransactionalException;
import com.mlnx.chronic.mapper.TBloodPressureSettingMapper;
import com.mlnx.chronic.mapper.TBloodSugarSettingMapper;
import com.mlnx.chronic.mapper.TGroupPatientMapper;
import com.mlnx.chronic.mapper.TPhoneValidMapper;
import com.mlnx.chronic.mapper.TUserDocMapper;
import com.mlnx.chronic.mapper.TUserExtMapper;
import com.mlnx.chronic.mapper.TUserFriendsMapper;
import com.mlnx.chronic.mapper.TUserMapper;
import com.mlnx.chronic.mapper.TVoipAccountMapper;
import com.mlnx.chronic.repo.PatientRepository;
import com.mlnx.chronic.service.UserService;
import com.mlnx.chronic.util.ChronicResponse;
import com.mlnx.chronic.util.EnumCollection;
import com.mlnx.chronic.util.PropertiyUtil;
import com.mlnx.chronic.util.RegistVoip;
import com.mlnx.chronic.util.StringUtil;
import com.mlnx.chronic.util.EnumCollection.ResponseCode;
import com.mlnx.chronic.vo.DocVo;
import com.mlnx.chronic.vo.FriendsInfo;
import com.mlnx.chronic.vo.RegistUser;
import com.mlnx.chronic.vo.UsrInfo;
import com.mlnx.chronic.vo.UsrVoipInfo;

import java.util.ArrayList;

@Transactional(rollbackFor = TransactionalException.class)
@Service
public class UserServiceImp implements UserService {

	@Autowired
	private TUserMapper tUserMapper;

	@Autowired
	private TUserExtMapper tUserExtMapper;

	@Autowired
	private TUserDocMapper tUserDocMapper;

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

	@Autowired
	private TVoipAccountMapper tVoipAccountMapper;
	
	@Autowired
	private TGroupPatientMapper tGroupPatientMapper;

	/**
	 * 这里是后台进行注册
	 */
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
				patient.setBirthday(new Date());//设置默认的出生日期为当前日期
				patient.setGender(Gender.MALE);//设置默认性别为男
				patient.setName("张三");//设置默认的姓名为张三
				int patientId = patientRepository.save(patient);
				TUserExt userExt = new TUserExt(patientId, userId);
				tUserExtMapper.insert(userExt);
				RegistVoip.regist(userId, user.getPhone(), tVoipAccountMapper);
				return new ChronicResponse(EnumCollection.ResponseCode.SUCCESS);
			} else {
				return new ChronicResponse(EnumCollection.ResponseCode.EXIST);
			}
		} catch (Exception e) {
			throw new RegisterException(e);
		}
	}

	@Override
	public Map<String, String> login(String phone, String password) {
		Map<String, String> map = new HashMap<String, String>();
		TUser user = tUserMapper.selectByPhone(phone);
		if (user == null) {
			map.put("responseCode",
					EnumCollection.ResponseCode.LOGIN_USERNAME_NOT_EXIST
							.getCode());
			map.put("msg", EnumCollection.ResponseCode.LOGIN_USERNAME_NOT_EXIST
					.getMsg());
			return map;
		}
		if (user.getPassword().equals(password)) {
			map.put("responseCode",
					EnumCollection.ResponseCode.LOGIN_SUCCESS.getCode());
			map.put("msg", EnumCollection.ResponseCode.LOGIN_SUCCESS.getMsg());
			map.put("obj", user.getId() + "");
			return map;
		} else {
			map.put("responseCode",
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

	/**
	 * 这里是手机号和验证码注册用户
	 */
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
					patient.setBirthday(new Date());//设置默认的出生日期为当前日期
					patient.setGender(Gender.MALE);//设置默认性别为男
					patient.setName("张三");//设置默认的姓名为张三
					int patientId = patientRepository.save(patient);
					TUserExt userExt = new TUserExt(patientId, userId);
					tUserExtMapper.insert(userExt);
					RegistVoip.regist(userId, u.getPhone(), tVoipAccountMapper);
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
			throw new RegisterException(e);
		}

	}

	@Override
	public ChronicResponse addFriend(TUserFriends tUserFriends) {
		// 判断用户是否已发送添加请求
		TUserFriends result = tUserFriendsMapper
				.selectByUserFriends(tUserFriends);
		if (result == null) {
			tUserFriends.setConfirm(0);// 0表示用户只是发起添加好友请求
			if (tUserFriends.getGroupId() == 1) {// 用户组是家庭组的话，默认设置公开，其他组不公开
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
	public ChronicResponse confirmAndCancel(int id,
			int confirm,int groupId) throws Exception {
		try {
			TUserFriends u = tUserFriendsMapper.selectByPrimaryKey(id);
			if (u.getConfirm() == 1) {
				return new ChronicResponse(
						EnumCollection.ResponseCode.ADD_FRIENDS_SUCCESS_ALREADY);
			}
			if (confirm == 1) {// 用户同意的话,用户请求的好友关系生效
				TUserFriends tUserFriends = new TUserFriends();
				tUserFriends.setId(id);
				tUserFriends.setConfirm(confirm);
				tUserFriendsMapper.updateByPrimaryKey(tUserFriends);
				
				TGroupPatient groupPatient = new TGroupPatient();//用户请求的好友添加到指定分组
				groupPatient.setGroupId(u.getGroupId());
				groupPatient.setPatientId(u.getFriendId());
				tGroupPatientMapper.insert(groupPatient);
				// 同时添加对方为好友
				toBeFriend(u,groupId);
				TGroupPatient group = new TGroupPatient();//用户接受的好友添加到指定分组
				group.setGroupId(groupId);
				group.setPatientId(u.getUserId());
				tGroupPatientMapper.insert(group);
				return new ChronicResponse(
						EnumCollection.ResponseCode.ADD_FRIENDS_SUCCESS);
			} else if (confirm == 0) { // 用户不同意就删除这条请求记录
				tUserFriendsMapper.deleteByPrimaryKey(id);
				return new ChronicResponse(
						EnumCollection.ResponseCode.ADD_FRIENDS_CANCEL);
			}
		} catch (Exception e) {
			throw new TransactionalException(StringUtil.friendConfirmerror);
		}
		return new ChronicResponse(
				EnumCollection.ResponseCode.ADD_FRIENDS_ERROR);

	}

	private void toBeFriend(TUserFriends u,int groupId) {
		TUserFriends uu = new TUserFriends();
		uu.setFriendId(u.getUserId());
		uu.setUserId(u.getFriendId());
		uu.setGroupId(groupId);//添加对方到自己组下
		uu.setFriendRemark(u.getRemark());
		uu.setFouce(1);
		uu.setOpen(0);
		uu.setConfirm(1);
		tUserFriendsMapper.insert(uu);
	}

	@Override
	public ChronicResponse modifyFriendMark(TUserFriends tUserFriends) {
		try {
			tUserFriendsMapper.updateFriendRemark(tUserFriends);
			return new ChronicResponse(
					EnumCollection.ResponseCode.UPDATE_FRIEND_REMARK_SUCCESS);
		} catch (Exception e) {
			return new ChronicResponse(ResponseCode.UPDATE_FRIEND_REMARK_ERROR);
		}
	}

	@Override
	public ChronicResponse getCode(String phone) {
		HashMap<String, Object> result = null;
		// 初始化SDK
		CCPRestSmsSDK restAPI = new CCPRestSmsSDK();

		// ******************************注释*********************************************
		// *初始化服务器地址和端口 *
		// *沙盒环境（用于应用开发调试）：restAPI.init("sandboxapp.cloopen.com", "8883");*
		// *生产环境（用户应用上线使用）：restAPI.init("app.cloopen.com", "8883"); *
		// *******************************************************************************
		restAPI.init("app.cloopen.com", "8883");

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
		result = restAPI.sendTemplateSMS(phone, "68124",
				new String[] { String.valueOf(code), "5" });

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
			return new ChronicResponse(ResponseCode.GET_CODE_ERROR);
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
		return new ChronicResponse(ResponseCode.GET_CODE_SUCCESS);
	}

	/**
	 * 纯修改手机密码
	 */
	@Override
	public ChronicResponse modifyPassword(TUser user) {
		if (user.getPhone() != null) {
			user.setPhone(null);
		}
		try {
			tUserMapper.updateByPrimaryKey(user);
			return new ChronicResponse(
					EnumCollection.ResponseCode.UPDATE_USER_PASSWORD_SUCCESS);
		} catch (Exception e) {
			return new ChronicResponse(
					EnumCollection.ResponseCode.UPDATE_USER_PASSWORD_ERROR);
		}
	}

	/**
	 * 查看是否有权限
	 */
	@Override
	public ChronicResponse havePermission(TUserFriends tUserFriends) {
		TUserFriends tuf = tUserFriendsMapper
				.findByUserIdAndFriendId(tUserFriends);
		if (tuf.getOpen() == 0) {
			return new ChronicResponse(
					EnumCollection.ResponseCode.NO_PERMISSION_OPEN);
		} else if (tuf.getOpen() == 1) {
			return new ChronicResponse(
					EnumCollection.ResponseCode.PERMISSION_OPEN_ALLOWED);
		}
		return null;//
	}

	@Transactional(rollbackFor=RegisterException.class)
	@Override
	public ChronicResponse updateUserExt(TUserExt user) {
		try {
			tUserExtMapper.updateByUserId(user);
			//更新心电服务器的病人数据
//			TUserExt u = tUserExtMapper.selectByUserId(user.getUserId());
//			Patient p = changeToECGPatient(u);
//			patientRepository.updatePatient(p);
			return new ChronicResponse(ResponseCode.UPDATE_USER_EXT_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return new ChronicResponse(ResponseCode.UPDATE_USER_EXT_ERROR);
		}
	}

	private Patient changeToECGPatient(TUserExt u) {
		Patient p = new Patient();
		p.setId(u.getPatientId());
		p.setName(u.getName());
		p.setBirthday(u.getBirthday());
		if("女".endsWith(u.getSex())){
			p.setGender(Gender.FEMALE);
		}
		return p;
	}

	@Override
	public List<UsrInfo> findUserListByIds(List<Integer> list) {
		return tUserExtMapper.findUserListByIds(list);
	}

	@Override
	public List<UsrVoipInfo> findVoipAccountList(List<Integer> list) {

		return tVoipAccountMapper.findVoipAccountList(list);
	}

	@Override
	public List<FriendsInfo> getFriendsIdsByIdAndGroupId(int id, int groupId) {
		TUserFriends tUserFriends = new TUserFriends(id, groupId);
		return tUserFriendsMapper.selectIdsByIdAndGroupId(tUserFriends);
	}

	@Override
	public ChronicResponse addFriendByPhone(int id, int friend_id,
			String remark, int groupId) {
		TUserFriends tUserFriends = new TUserFriends();
		tUserFriends.setUserId(id);
		tUserFriends.setFriendId(friend_id);
		tUserFriends.setGroupId(groupId);
		tUserFriends.setConfirm(0);// 0表示暂时未通过好友验证
		tUserFriends.setRemark(remark);
		tUserFriendsMapper.insert(tUserFriends);
		return new ChronicResponse(ResponseCode.ADD_FRIEND_REQUEST_SUCCESS);
	}

	@Override
	public UsrInfo findFriendByPhone(String phone) {
		TUser tu = tUserMapper.selectByPhone(phone);
		List<Integer> list = new ArrayList<Integer>();
		list.add(tu.getId());
		List<UsrInfo> uis = tUserExtMapper.findUserListByIds(list);
		if (uis == null || uis.size() == 0) {
			return null;
		} else {
			return uis.get(0);
		}
	}

	@Override
	public ChronicResponse updateUserPassword(RegistUser u) {
		// 验证手机号是否存在
		TUser tuser = tUserMapper.selectByPhone(u.getPhone());
		if (tuser != null) {
			// 验证手机验证码
			TPhoneValid ph = tPhoneValidMapper.selectByPhone(u.getPhone());
			if (ph != null && u.getCode().equals(ph.getValidcode())) {
				TUser user = tUserMapper.selectByPhone(u.getPhone());
				user.setPassword(u.getPassword());
				tUserMapper.updateByPrimaryKey(user);
				return new ChronicResponse(
						ResponseCode.UPDATE_PHONE_PASSWORD_SUCCESS);
			} else {
				return new ChronicResponse(
						ResponseCode.UPDATE_PHONE_VALID_NOT_RIGHT);
			}
		} else {
			return new ChronicResponse(ResponseCode.UPDATE_PHONE_NOT_EXIST);
		}
	}

	@Override
	public Map<String, Object> findDoctorInfo(Integer doctorId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			TUserDoc doc = tUserDocMapper.findDoctorInfo(doctorId);
			map.put(StringUtil.responseCode,
					ResponseCode.FIND_DOCTOR_INFO_SUCCESS.getCode());
			map.put(StringUtil.responseMsg,
					ResponseCode.FIND_DOCTOR_INFO_SUCCESS.getMsg());
			map.put(StringUtil.responseObj, doc);
			return map;
		} catch (Exception e) {
			map.put(StringUtil.responseCode,
					ResponseCode.FIND_DOCTOR_INFO_ERROR.getCode());
			map.put(StringUtil.responseMsg,
					ResponseCode.FIND_DOCTOR_INFO_ERROR.getMsg());
			return map;
		}
	}

	@Override
	public ChronicResponse addDoctorFriend(int id, int friend_id,
			String remark, int groupId) {
		try {
			TUserFriends tUserFriends = new TUserFriends();
			tUserFriends.setUserId(id);
			tUserFriends.setFriendId(friend_id);
			tUserFriends.setGroupId(groupId);
			tUserFriends.setConfirm(0);// 1表示直接通过好友认证,未认证
			tUserFriends.setRemark(remark);
			tUserFriendsMapper.insert(tUserFriends);
			return new ChronicResponse(ResponseCode.ADD_DOCTOR_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return new ChronicResponse(ResponseCode.ADD_DOCTOR_ERROR);
		}
	}

	@Override
	public TUserDoc findDoctorByPhone(String phone) {
		TUserDoc doc = tUserDocMapper.selectByPhone(phone);
		return doc;
	}

	@Override
	public List<UsrInfo> findDoctorListByIds(List<Integer> list) {
		return tUserDocMapper.findUserListByIds(list);
	}

	@Override
	public ChronicResponse updateDoc(TUserDoc doctor) {
		try {
			tUserDocMapper.updateByPrimaryKey(doctor);
			return new ChronicResponse(ResponseCode.UPDATE_DOCTOR_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return new ChronicResponse(ResponseCode.UPDATE_DOCTOR_ERROR);
		}
	}

	@Override
	public List<DocVo> findDoctorVoListByIds(List<Integer> list) {
		return tUserDocMapper.findDocVoListByIds(list);
	}
	

}
