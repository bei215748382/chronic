package com.mlnx.chronic.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mlnx.chronic.entity.TBloodPressureSetting;
import com.mlnx.chronic.entity.TBloodSugarSetting;
import com.mlnx.chronic.entity.TUser;
import com.mlnx.chronic.entity.TUserFriends;
import com.mlnx.chronic.mapper.TPhoneValidMapper;
import com.mlnx.chronic.vo.RegistUser;
import com.mlnx.springmvc.service.UserService;
import com.mlnx.springmvc.util.ChronicResponse;

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
	public ChronicResponse registByValidcode(@RequestBody RegistUser u)
			throws Exception {
		return userService.registUser(u);
	}

	/**
	 * 获取手机验证码
	 * 
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = "getCode/{phone}")
	@ResponseBody
	public Map<String, Object> sendCode(@PathVariable String phone) {
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
	@RequestMapping(value = "login/{phone}/{password}")
	@ResponseBody
	public Map<String, String> login(@PathVariable("phone") String phone,
			@PathVariable("password") String password) {
		return userService.login(phone, password);
	}

	@RequestMapping(value = "modify")
	@ResponseBody
	public ChronicResponse modify(@RequestBody TUser user) {
		return userService.modifyPassword(user);
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
	@RequestMapping(value = "get/friends/by/{id}/{groupId}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public List<TUserFriends> getFriendsById(@PathVariable int id,
			@PathVariable int groupId) {
		return userService.getFriendsByIdAndGroupId(id, groupId);

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
	@RequestMapping(value = "confirmFriend/{user_id}/list", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public List<TUserFriends> confirmFriendList(@PathVariable("user_id") int id) {
		return userService.confirmFriendList(id);
	}

	/**
	 * 确认添加好友/取消
	 * 
	 * @param tUserFriends
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "confirmAndCancel/{id}/{confirm}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ChronicResponse confirmAndCancel (
			@PathVariable("confirm") int confirm,
			@PathVariable int id) throws Exception {
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
	@RequestMapping(value = "modify/friend/{id}/{friendRemark}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ChronicResponse modifyFriendMark(@PathVariable int id,@PathVariable("friendRemark") String mark) {
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
	@RequestMapping(value = "permission/{uid}/{fid}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ChronicResponse havePermission(@PathVariable Integer uid,@PathVariable Integer fid) {
		TUserFriends tu = new TUserFriends();
		tu.setUserId(fid);
		tu.setFriendId(uid);
		return userService.havePermission(tu);
	}
}
