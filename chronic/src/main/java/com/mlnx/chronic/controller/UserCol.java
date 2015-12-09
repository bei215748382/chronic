package com.mlnx.chronic.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mlnx.chronic.entity.TBloodPressureSetting;
import com.mlnx.chronic.entity.TBloodSugarSetting;
import com.mlnx.chronic.entity.TUser;
import com.mlnx.chronic.entity.TUserExt;
import com.mlnx.chronic.entity.TUserFriends;
import com.mlnx.chronic.mapper.TPhoneValidMapper;
import com.mlnx.chronic.vo.FriendsInfo;
import com.mlnx.chronic.vo.RegistUser;
import com.mlnx.chronic.vo.UsrInfo;
import com.mlnx.chronic.vo.UsrVoipInfo;
import com.mlnx.springmvc.service.UserService;
import com.mlnx.springmvc.util.ChronicResponse;
import com.mlnx.springmvc.util.StringUtil;
import com.mlnx.springmvc.util.EnumCollection.ResponseCode;
import com.mlnx.springmvc.util.FileUtil;

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
	 * 手机验证码注册用户
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
		user.setId(id);
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
			user.setId(id);
			String pic = FileUtil.savePic(request, in);
			user.setPic(pic);
			return userService.updateUserExt(user);
		} catch (Exception e) {
			return new ChronicResponse(ResponseCode.UPLOAD_PIC_ERROR);
		}
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
	public Map<String,Object> confirmFriendList(@RequestHeader("id") int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<TUserFriends> tus = userService.confirmFriendList(id);
		if (tus == null || tus.size()==0) {
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
			@RequestHeader("confirm") int confirm, @RequestHeader("id") int id)
			throws Exception {
		TUserFriends tUserFriends = new TUserFriends();
		tUserFriends.setId(id);
		return userService.confirmAndCancel(tUserFriends, confirm);
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
}
