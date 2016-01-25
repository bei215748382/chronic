package com.mlnx.chronic.mapper2;

import com.mlnx.chronic.entity2.TAdminUser;
import java.util.List;

public interface TAdminUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TAdminUser record);

    TAdminUser selectByPrimaryKey(Integer id);

    List<TAdminUser> selectAll();

    int updateByPrimaryKey(TAdminUser record);

	TAdminUser selectByUsername(String username);
}