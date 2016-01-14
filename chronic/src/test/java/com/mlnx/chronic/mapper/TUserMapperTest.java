package com.mlnx.chronic.mapper;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mlnx.chronic.entity.TUserFriends;
import com.mlnx.chronic.service.UserService;
import com.mlnx.chronic.vo.UserVo;

public class TUserMapperTest extends TestBase {
	@Autowired
	private TUserMapper tUserMapper;
	@Autowired
	private TReportMapper tReportMapper;
	
	@Autowired
	private TUserFriendsMapper tUserFriendsMapper;
	
	@Autowired 
	private UserService userService;

	@Test
	public void selectTReportsByPrimaryKey() {
		UserVo userVo = tUserMapper.selectTReportsByPrimaryKey(12);
		userVo.gettReports();
		
	}
	
	@Test
	public void insert() {
		TUserFriends tUserFriends = new TUserFriends();
		int userId = 1;
		int friendId = 4;
		int fouce = 1;//1表示进行关注、暂时没用
		int open = 1;//1表示自己的信息给对方开放
		int groupId= 1;//1表示家庭组，2表示医生组，3表示好友组
		String remark  = "我是莫莫莫，请求添加好友";//添加好友时候的备注
		String friendRemark = "爸";
		tUserFriends.setUserId(userId);//请求加好友的用户id
		tUserFriends.setFriendId(friendId);//请求被添加好友的用户id
		tUserFriends.setFouce(fouce);
		tUserFriends.setOpen(open);
		tUserFriends.setGroupId(groupId);
		tUserFriends.setRemark(remark);
		tUserFriends.setFriendRemark(friendRemark);
		System.out.println(userService.addFriend(tUserFriends));
		
	}

}
