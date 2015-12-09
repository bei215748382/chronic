package com.mlnx.springmvc.service;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
		ChronicResponse str = userService.getCode(phone);
		System.out.println(str.toString());
	}

	@Test
	public void testRegistUserTUser() throws Exception {
		RegistUser user = new RegistUser();
		user.setPhone("15867404048");
		user.setPassword("123456");
		user.setCode(21977);
		ChronicResponse str = userService.registUser(user);
		System.out.println(str.toString());
	}

	@Test
	public void testLogin() {
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
		System.out.println(a == 318094);

	}

	@Test
	public void test() {
		String httpUrl = "http://localhost:8080/chronic/user/update/userExt/pic";
		BufferedReader reader = null;
		String result = null;
		StringBuffer sbf = new StringBuffer();
		try {
			URL url = new URL(httpUrl);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("id", "1");
			connection.setDoOutput(true);
			OutputStream os = connection.getOutputStream();
			FileInputStream fis = new FileInputStream(
					"v://158_a37HvLUtMH35k3dhv3dl_square.jpg");
			byte[] b = new byte[1024];
			int i = 0;
			while ((i = fis.read(b)) > 0) {
				/* 写入流 */
				os.write(b, 0, i);
			}
			connection.connect();
			Map map = connection.getHeaderFields();
			for (Object key : map.keySet()) {
				System.out.println(key + " : " + map.get(key));
			}

			InputStream is = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sbf.append(strRead);
				sbf.append("\r\n");
			}
			reader.close();
			result = sbf.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getUserInfo(){
		List<Integer> list = new ArrayList<Integer>();
		list.add(15);
		System.out.println(userService.findUserListByIds(list));
	}

	@Test
	public void getUserVoipInfo(){
		List<Integer> list = new ArrayList<Integer>();
		list.add(15);
		list.add(29);
		System.out.println(userService.findVoipAccountList(list));
	}
	
	@Test
	public void getFriendsById(){
		System.out.println(userService.getFriendsIdsByIdAndGroupId(1, 1));
	}
}
