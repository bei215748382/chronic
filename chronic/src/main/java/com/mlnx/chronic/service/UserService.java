package com.mlnx.chronic.service;

import java.util.List;
import java.util.Map;

import com.mlnx.chronic.entity.TBloodPressureSetting;
import com.mlnx.chronic.entity.TBloodSugarSetting;
import com.mlnx.chronic.entity.TUser;
import com.mlnx.chronic.entity.TUserExt;
import com.mlnx.chronic.entity.TUserFriends;
import com.mlnx.chronic.util.ChronicResponse;
import com.mlnx.chronic.vo.FriendsInfo;
import com.mlnx.chronic.vo.RegistUser;
import com.mlnx.chronic.vo.UsrInfo;
import com.mlnx.chronic.vo.UsrVoipInfo;

public interface UserService {

	// 注册用户
	public ChronicResponse registUser(TUser user) throws Exception;

	// 登入
	public Map<String, String> login(String phone, String password);

	// 修改血压阀值
	public ChronicResponse updateBloodPressureSetting(
			TBloodPressureSetting bloodPressureSetting);

	//修改血糖阀值
	public ChronicResponse updateBloodSugarSetting(
			TBloodSugarSetting bloodSugarSetting);

	//手机验证码注册用户
	public ChronicResponse registUser(RegistUser u) throws Exception;
	
	//添加好友
	public ChronicResponse addFriend(TUserFriends tUserFriends);

	//获取家庭组下好友列表
	public List<TUserFriends> getFriendsByIdAndGroupId(int id,int groupId);

	//获取添加请求的好友列表
	public List<TUserFriends> confirmFriendList(int id);

	//确认好友还是取消
	public ChronicResponse confirmAndCancel(TUserFriends tUserFriends,int confirm) throws Exception ;

	//修改好友备注
	public ChronicResponse modifyFriendMark(TUserFriends tUserFriends);

	//获取短信验证码
	public ChronicResponse getCode(String phone);

	//修改密码
	public ChronicResponse modifyPassword(TUser user);

	//是否有权限看
	public ChronicResponse havePermission(TUserFriends tUserFriends);

	//注册修改详细信息
	public ChronicResponse updateUserExt(TUserExt user);

	/**
	 * 根据id获取用户详情
	 * @param list
	 * @return
	 */
	public List<UsrInfo> findUserListByIds(List<Integer> list);

	/**
	 * 获取voip账号
	 * @param list
	 * @return
	 */
	public List<UsrVoipInfo> findVoipAccountList(List<Integer> list);

	/**
	 * 根据用户id、组id获取好友id
	 * @param id
	 * @param groupId
	 * @return
	 */
	public List<FriendsInfo> getFriendsIdsByIdAndGroupId(int id, int groupId);

	/**
	 * 根据手机号添加好友
	 * @param id
	 * @param phone
	 * @param remark
	 * @return
	 */
	public ChronicResponse addFriendByPhone(int id, int friend_id, String remark,int groupId);

	/**
	 * 根据手机号查找用户
	 * @param phone
	 * @return
	 */
	public UsrInfo findFriendByPhone(String phone);



}
