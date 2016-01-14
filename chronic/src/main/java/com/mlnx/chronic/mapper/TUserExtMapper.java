package com.mlnx.chronic.mapper;

import java.util.List;

import com.mlnx.chronic.entity.TUserExt;
import com.mlnx.chronic.vo.UsrInfo;

public interface TUserExtMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUserExt record);

    TUserExt selectByPrimaryKey(Integer id);

    List<TUserExt> selectAll();

    int updateByPrimaryKey(TUserExt record);

	List<UsrInfo> findUserListByIds(List<Integer> list);

	/**
	 * 根据用户id修改用户详细信息
	 * @param user
	 */
	void updateByUserId(TUserExt user);
}