package com.mlnx.chronic.mapper;

import java.util.List;

import com.mlnx.chronic.entity.TUserFriends;
import com.mlnx.chronic.vo.FriendsInfo;

public interface TUserFriendsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUserFriends record);

    TUserFriends selectByPrimaryKey(Integer id);

    List<TUserFriends> selectAll();

    int updateByPrimaryKey(TUserFriends record);

    /**
     * 查找是否有相同的记录存在
     * @param tUserFriends
     * @return
     */
	TUserFriends selectByUserFriends(TUserFriends tUserFriends);

	/**
	 * 根据用户id和用户组获取好友列表
	 * @param tUserFriends
	 * @return
	 */
	List<TUserFriends> selectByIdAndGroupId(TUserFriends tUserFriends);

	/**
	 * 获取未通过请求的好友列表
	 * @param tUserFriends
	 * @return
	 */
	List<TUserFriends> selectByUserIdAndUNConfirmed(TUserFriends tUserFriends);

	/**
	 * 修改好友备注
	 */
	void updateFriendRemark(TUserFriends tUserFriends);

	/**
	 * 根据id和好友id查找数据
	 * @param tUserFriends
	 * @return
	 */
	TUserFriends findByUserIdAndFriendId(TUserFriends tUserFriends);

	/**
	 * 返回id列表
	 * @param tUserFriends
	 * @return
	 */
	List<FriendsInfo> selectIdsByIdAndGroupId(TUserFriends tUserFriends);
}