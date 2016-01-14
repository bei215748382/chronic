package com.mlnx.chronic.mapper;

import java.util.List;

import com.mlnx.chronic.entity.TIdentity;

public interface TIdentityMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(TIdentity record);

	TIdentity selectByPrimaryKey(Integer id);

	List<TIdentity> selectAll();

	int updateByPrimaryKey(TIdentity record);
}