package com.mlnx.chronic.mapper;

import java.util.List;

import com.mlnx.chronic.entity.TUserFriends;

public interface TUserFriendsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_friends
     *
     * @mbggenerated Thu Nov 12 15:45:41 CST 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_friends
     *
     * @mbggenerated Thu Nov 12 15:45:41 CST 2015
     */

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_friends
     *
     * @mbggenerated Thu Nov 12 15:45:41 CST 2015
     */
    TUserFriends selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_friends
     *
     * @mbggenerated Thu Nov 12 15:45:41 CST 2015
     */
    List<TUserFriends> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_friends
     *
     * @mbggenerated Thu Nov 12 15:45:41 CST 2015
     */
    int updateByPrimaryKey(TUserFriends record);
}