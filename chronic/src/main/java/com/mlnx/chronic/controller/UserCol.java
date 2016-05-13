package com.mlnx.chronic.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mlnx.chronic.entity.TBloodPressureSetting;
import com.mlnx.chronic.entity.TBloodSugarSetting;
import com.mlnx.chronic.entity.TUser;
import com.mlnx.chronic.entity.TUserDoc;
import com.mlnx.chronic.entity.TUserExt;
import com.mlnx.chronic.entity.TUserFriends;
import com.mlnx.chronic.mapper.TPhoneValidMapper;
import com.mlnx.chronic.service.UserService;
import com.mlnx.chronic.util.ChronicResponse;
import com.mlnx.chronic.util.FileUtil;
import com.mlnx.chronic.util.StringUtil;
import com.mlnx.chronic.util.EnumCollection.ResponseCode;
import com.mlnx.chronic.vo.DocVo;
import com.mlnx.chronic.vo.FriendsInfo;
import com.mlnx.chronic.vo.RegistUser;
import com.mlnx.chronic.vo.UsrInfo;
import com.mlnx.chronic.vo.UsrVoipInfo;

@Controller
@RequestMapping(value = "/user")
public class UserCol {

	@Autowired
	private UserService userService;

	@Autowired
	private TPhoneValidMapper tPhoneValidMapper;

	/**
	 * 手机端注册用户
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "regist")
	@ResponseBody
	public ChronicResponse regist(TUser user) throws Exception {
		return userService.registUser(user);
	}

	/**
	 * 手机验证码注册用户--病人
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "registByValidcode", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ChronicResponse registByValidcode(
			@RequestHeader("phone") String phone,
			@RequestHeader("password") String password,
			@RequestHeader("code") String code) throws Exception {
		RegistUser u = new RegistUser();
		u.setPhone(phone);
		u.setPassword(password);
		u.setCode(Integer.parseInt(code));
		return userService.registUser(u);
	}

	/**
	 * 获取手机验证码
	 * 
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = "getCode")
	@ResponseBody
	public ChronicResponse sendCode(@RequestHeader("phone") String phone) {
		return userService.getCode(phone);
	}

	/**
	 * 根据手机号、密码登入
	 * 
	 * @param phone
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "login")
	@ResponseBody
	public Map<String, String> login(@RequestHeader("phone") String phone,
			@RequestHeader("password") String password) {
		return userService.login(phone, password);
	}

	/**
	 * 修改密码
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "modify")
	@ResponseBody
	public ChronicResponse modify(@RequestHeader("id") int id,
			@RequestHeader("password") String password) {
		TUser user = new TUser();
		user.setId(id);
		user.setPassword(password);
		return userService.modifyPassword(user);
	}

	/**
	 * 注册填写额外信息
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "update/userExt")
	@ResponseBody
	public ChronicResponse updateUserExt(@RequestHeader("id") int id,
			@RequestBody TUserExt user) {
		user.setUserId(id);
		return userService.updateUserExt(user);
	}

	/**
	 * 上传用户头像
	 * 
	 * @param request
	 * @param id
	 * @param in
	 * @return
	 */
	@RequestMapping(value = "update/userExt/pic")
	@ResponseBody
	public ChronicResponse updateUserExtPic(HttpServletRequest request,
			@RequestHeader("id") int id, InputStream in) {
		try {
			TUserExt user = new TUserExt();
			user.setUserId(id);
			String pic = FileUtil.savePic(request, in);
			user.setPic(pic);
			return userService.updateUserExt(user);
		} catch (Exception e) {
			return new ChronicResponse(ResponseCode.UPLOAD_PIC_ERROR);
		}
	}

	/**
	 * 手机验证码修改密码
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "modifyPasswordByValidcode", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ChronicResponse modifyPasswordByValidcode(
			@RequestHeader("phone") String phone,
			@RequestHeader("password") String password,
			@RequestHeader("code") String code) throws Exception {
		RegistUser u = new RegistUser();
		u.setPhone(phone);
		u.setPassword(password);
		u.setCode(Integer.parseInt(code));
		return userService.updateUserPassword(u);
	}

	/**
	 * 获取用户详细信息
	 * 
	 * @param request
	 * @param id
	 * @param in
	 * @return
	 */
	@RequestMapping(value = "find/user/list", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> findUserList(@RequestBody List<Integer> list) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<UsrInfo> userInfo = userService.findUserListByIds(list);
			map.put("responseCode",
					ResponseCode.FIND_USER_INFO_SUCCESS.getCode());
			map.put("msg", ResponseCode.FIND_USER_INFO_SUCCESS.getMsg());
			map.put("objList", userInfo);
			return map;
		} catch (Exception e) {
			map.put("responseCode", ResponseCode.FIND_USER_INFO_ERROR.getCode());
			map.put("msg", ResponseCode.FIND_USER_INFO_ERROR.getMsg());
			return map;
		}
	}

	@RequestMapping(value = "find/voip/account/list", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> findVoipAccountList(
			@RequestBody List<Integer> list) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<UsrVoipInfo> usrVoipInfo = userService
					.findVoipAccountList(list);
			map.put("responseCode",
					ResponseCode.FIND_VOIP_ACCOUNT_SUCCESS.getCode());
			map.put("msg", ResponseCode.FIND_VOIP_ACCOUNT_SUCCESS.getMsg());
			map.put("objList", usrVoipInfo);
			return map;
		} catch (Exception e) {
			map.put("responseCode",
					ResponseCode.FIND_VOIP_ACCOUNT_ERROR.getCode());
			map.put("msg", ResponseCode.FIND_VOIP_ACCOUNT_ERROR.getMsg());
			return map;
		}
	}

	/**
	 * 用户设置血压
	 * 
	 * @param bloodPressureSetting
	 * @return
	 */
	@RequestMapping(value = "bloodPressureSetting")
	@ResponseBody
	public ChronicResponse bloodPressureSetting(
			TBloodPressureSetting bloodPressureSetting) {
		return userService.updateBloodPressureSetting(bloodPressureSetting);
	}

	/**
	 * 用户配置血糖设置
	 * 
	 * @param bloodSugarSetting
	 * @return
	 */
	@RequestMapping(value = "bloodSugarSetting")
	@ResponseBody
	public ChronicResponse bloodSugarSetting(
			TBloodSugarSetting bloodSugarSetting) {
		return userService.updateBloodSugarSetting(bloodSugarSetting);
	}

	/**
	 * 获取好友列表
	 * 
	 * @param tUserFriends
	 * @return
	 */
	@RequestMapping(value = "get/friends", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> getFriendsById(@RequestHeader("id") int id,
			@RequestHeader("groupId") int groupId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<FriendsInfo> ids = userService.getFriendsIdsByIdAndGroupId(id,
					groupId);
			map.put("responseCode",
					ResponseCode.FIND_FRIENDS_COMFIRMED_SUCCESS.getCode());
			map.put("msg", ResponseCode.FIND_FRIENDS_COMFIRMED_SUCCESS.getMsg());
			map.put("objList", ids);
			return map;
		} catch (Exception e) {
			map.put("responseCode",
					ResponseCode.FIND_FRIENDS_COMFIRMED_ERROR.getCode());
			map.put("msg", ResponseCode.FIND_FRIENDS_COMFIRMED_ERROR.getMsg());
			return map;
		}

	}

	/**
	 * 根据手机号获取用户详细信息
	 * 
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = "find/friend/by/phone", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> findFriendByPhone(
			@RequestHeader("phone") String phone) {
		Map<String, Object> map = new HashMap<String, Object>();
		UsrInfo u = userService.findFriendByPhone(phone);
		if (u == null) {
			map.put(StringUtil.responseCode,
					ResponseCode.FIND_USER_INFO_BY_PHONE_ERROR.getCode());
			map.put(StringUtil.responseMsg,
					ResponseCode.FIND_USER_INFO_BY_PHONE_ERROR.getMsg());
			return map;
		} else {
			map.put(StringUtil.responseCode,
					ResponseCode.FIND_USER_INFO_BY_PHONE_SUCCESS.getCode());
			map.put(StringUtil.responseMsg,
					ResponseCode.FIND_USER_INFO_BY_PHONE_SUCCESS.getMsg());
			map.put(StringUtil.responseObj, u);
			return map;
		}
	}

	/**
	 * 客户端发起添加好友请求
	 * 
	 * @param id
	 * @param friend_id
	 * @param remark
	 * @param groupId
	 * @return
	 */
	@RequestMapping(value = "add/friend/by/phone", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ChronicResponse addFriend(@RequestHeader("id") int id,
			@RequestHeader("friend_id") int friend_id,
			@RequestHeader("remark") String remark,
			@RequestHeader("groupId") int groupId) {
		return userService.addFriendByPhone(id, friend_id, remark, groupId);
	}

	/**
	 * 主动添加好友
	 * 
	 * @param tUserFriends
	 * @return
	 */
	@RequestMapping(value = "add/friend", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ChronicResponse addFriend(@RequestBody TUserFriends tUserFriends) {
		return userService.addFriend(tUserFriends);

	}

	/**
	 * 被请求的列表
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "confirmFriend/list", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> confirmFriendList(@RequestHeader("id") int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<TUserFriends> tus = userService.confirmFriendList(id);
		if (tus == null || tus.size() == 0) {
			map.put(StringUtil.responseCode,
					ResponseCode.FIND_UNCONFIRMED_FRIEND_ERROR.getCode());
			map.put(StringUtil.responseMsg,
					ResponseCode.FIND_UNCONFIRMED_FRIEND_ERROR.getMsg());
			return map;
		} else {
			map.put(StringUtil.responseCode,
					ResponseCode.FIND_UNCONFIRMED_FRIEND_SUCCESS.getCode());
			map.put(StringUtil.responseMsg,
					ResponseCode.FIND_UNCONFIRMED_FRIEND_SUCCESS.getMsg());
			map.put(StringUtil.responseObjList, tus);
			return map;
		}
	}

	/**
	 * 确认添加好友/取消
	 * 
	 * @param tUserFriends
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "confirmAndCancel", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ChronicResponse confirmAndCancel(
			@RequestHeader("confirm") Integer confirm, @RequestHeader("id") Integer id,@RequestHeader("groupId") Integer groupId)
			throws Exception {
		return userService.confirmAndCancel(id, confirm,groupId);
	}

	/**
	 * 修改好友备注
	 * 
	 * @param mark
	 * @return
	 */
	@RequestMapping(value = "modify/friend", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ChronicResponse modifyFriendMark(@RequestHeader("id") int id,
			@RequestHeader("friendRemark") String mark) {
		TUserFriends tu = new TUserFriends();
		tu.setId(id);
		tu.setFriendRemark(mark);
		return userService.modifyFriendMark(tu);
	}

	/**
	 * 修改好友备注
	 * 
	 * @param mark
	 * @return
	 */
	@RequestMapping(value = "permission", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ChronicResponse havePermission(@RequestHeader("uid") Integer uid,
			@RequestHeader("fid") Integer fid) {
		TUserFriends tu = new TUserFriends();
		tu.setUserId(fid);
		tu.setFriendId(uid);
		return userService.havePermission(tu);
	}

	/**
	 * 获取医生用户详细信息
	 * 
	 * @param doctorId
	 * @return
	 */
	@RequestMapping(value = "find/doctor/info", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> findDoctorInfo(
			@RequestHeader("doctorId") Integer doctorId) {
		return userService.findDoctorInfo(doctorId);
	}

	/**
	 * 根据手机号获取医生用户详细信息
	 * 
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = "find/doctor/by/phone", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> findDoctorByPhone(
			@RequestHeader("phone") String phone) {
		Map<String, Object> map = new HashMap<String, Object>();
		TUserDoc u = userService.findDoctorByPhone(phone);
		if (u == null) {
			map.put(StringUtil.responseCode,
					ResponseCode.FIND_DOC_INFO_BY_PHONE_ERROR.getCode());
			map.put(StringUtil.responseMsg,
					ResponseCode.FIND_DOC_INFO_BY_PHONE_ERROR.getMsg());
			return map;
		} else {
			map.put(StringUtil.responseCode,
					ResponseCode.FIND_DOC_INFO_BY_PHONE_SUCCESS.getCode());
			map.put(StringUtil.responseMsg,
					ResponseCode.FIND_DOC_INFO_BY_PHONE_SUCCESS.getMsg());
			map.put(StringUtil.responseObj, u);
			return map;
		}
	}

	/**
	 * 主动添加医生好友
	 * 
	 * @param tUserFriends
	 * @return
	 */
	@RequestMapping(value = "add/doctor/friend", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ChronicResponse addDoctorFriend(@RequestHeader("id") int id,
			@RequestHeader("friend_id") int friend_id,
			@RequestHeader("remark") String remark,
			@RequestHeader("groupId") int groupId) {
		return userService.addDoctorFriend(id, friend_id, remark, groupId);

	}

	/**
	 * 根据病人id列表获取病人信息
	 * 
	 * @param list
	 * @return
	 */
	@RequestMapping(value = "find/doctor/list", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> findDoctorList(@RequestBody List<Integer> list) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<UsrInfo> userInfo = userService.findDoctorListByIds(list);
			map.put("responseCode",
					ResponseCode.FIND_DOC_INFO_SUCCESS.getCode());
			map.put("msg", ResponseCode.FIND_DOC_INFO_SUCCESS.getMsg());
			map.put("objList", userInfo);
			return map;
		} catch (Exception e) {
			map.put("responseCode", ResponseCode.FIND_DOC_INFO_ERROR.getCode());
			map.put("msg", ResponseCode.FIND_DOC_INFO_ERROR.getMsg());
			return map;
		}
	}
	
	/**
	 * 根据医生id列表获取医生信息
	 * 
	 * @param list
	 * @return
	 */
	@RequestMapping(value = "find/docVo/list", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> findDocVoList(@RequestBody List<Integer> list) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<DocVo> docs = userService.findDoctorVoListByIds(list);
			map.put("responseCode",
					ResponseCode.FIND_DOC_INFO_SUCCESS.getCode());
			map.put("msg", ResponseCode.FIND_DOC_INFO_SUCCESS.getMsg());
			map.put("objList", docs);
			return map;
		} catch (Exception e) {
			map.put("responseCode", ResponseCode.FIND_DOC_INFO_ERROR.getCode());
			map.put("msg", ResponseCode.FIND_DOC_INFO_ERROR.getMsg());
			return map;
		}
	}

	/**
	 * 更新医生信息成功
	 * 
	 * @param file
	 * @param request
	 * @param response
	 * @param doctor
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "update/doctor/info")
	public ChronicResponse updateDoctorInfo(MultipartFile file,
			HttpServletRequest request, HttpServletResponse response,
			TUserDoc doctor) throws IOException {
		if (file != null && file.getOriginalFilename() != "") {
			String pic = FileUtil.savePic(request, file);
			doctor.setPic(pic);
		}
		return userService.updateDoc(doctor);
	}

}
