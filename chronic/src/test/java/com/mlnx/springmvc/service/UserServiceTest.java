package com.mlnx.springmvc.service;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mlnx.chronic.entity.TUser;
import com.mlnx.chronic.entity.TUserFriends;
import com.mlnx.chronic.mapper.TestBase;
import com.mlnx.chronic.vo.RegistUser;
import com.mlnx.springmvc.util.ChronicResponse;

public class UserServiceTest extends TestBase {
	@Autowired
	private UserService userService;
	
	@Test
	public void testGetCode() {
		String phone = "15867404048";
		Map<String, Object> str = userService.getCode(phone);
		System.out.println(str.toString());
	}

	@Test
	public void testRegistUserTUser() throws Exception {
		RegistUser user = new RegistUser();
		user.setPhone("15867404048");
		user.setPassword("123456");
		user.setCode(318094);
		ChronicResponse str = userService.registUser(user);
		System.out.println(str.toString());
	}

	@Test
	public void testLogin(){
		String phone = "15867404047";
		String password = "12345";
		System.out.println(userService.login(phone, password));
	}

	@Test
	public void testUpdateBloodPressureSetting() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateBloodSugarSetting() {
		fail("Not yet implemented");
	}

	@Test
	public void testRegistUserTUserInteger() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddFriend() {
		TUserFriends tUserFriends = new TUserFriends();
		int userId = 1;
		int friendId = 5;
		int fouce = 1;// 1表示进行关注、暂时没用
		int open = 1;// 1表示自己的信息给对方开放
		int groupId = 1;// 1表示家庭组，2表示医生组，3表示好友组
		String remark = "我是莫莫莫，请求添加好友";// 添加好友时候的备注
		String friendRemark = "爸";
		tUserFriends.setUserId(userId);// 请求加好友的用户id
		tUserFriends.setFriendId(friendId);// 请求被添加好友的用户id
		tUserFriends.setFouce(fouce);
		tUserFriends.setOpen(open);
		tUserFriends.setGroupId(groupId);
		tUserFriends.setRemark(remark);
		tUserFriends.setFriendRemark(friendRemark);
		System.out.println(userService.addFriend(tUserFriends));
	}

	@Test
	public void testGetFriendsByIdAndGroupId() {
		System.out.println(userService.getFriendsByIdAndGroupId(1, 1));
	}

	@Test
	public void testConfirmFriendList() {
		System.out.println(userService.confirmFriendList(2));
	}

	@Test
	public void testConfirmAndCancel() throws Exception {
		TUserFriends tu = new TUserFriends();
		tu.setId(2);
		System.out.println(userService.confirmAndCancel(tu, 0));
	}

	@Test
	public void testModifyFriendMark() {
		Integer a = 318094;
		Integer b = 318094;
		System.out.println(a==318094);
		
	}

}
