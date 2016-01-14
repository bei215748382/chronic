package com.mlnx.chronic.mapper;

import java.util.List;

import com.mlnx.chronic.entity.TUser;
import com.mlnx.chronic.vo.UserVo;

public interface TUserMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(TUser record);

	TUser selectByPrimaryKey(Integer id);

	List<TUser> selectAll();

	int updateByPrimaryKey(TUser record);

	/**
	 * 获取用户信息和报告信息
	 * 
	 * @param id
	 * @return
	 */
	UserVo selectTReportsByPrimaryKey(Integer id);

	/**
	 * 根据用户手机号获取用户
	 * 
	 * @param phone
	 * @return
	 */
	TUser selectByPhone(String phone);

}