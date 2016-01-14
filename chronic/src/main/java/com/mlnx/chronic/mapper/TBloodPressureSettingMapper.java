package com.mlnx.chronic.mapper;

import java.util.List;

import com.mlnx.chronic.entity.TBloodPressureSetting;
import com.mlnx.chronic.vo.UserSetting;

public interface TBloodPressureSettingMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(TBloodPressureSetting record);

    TBloodPressureSetting selectByPrimaryKey(Integer id);

    List<TBloodPressureSetting> selectAll();

    int updateByPrimaryKey(TBloodPressureSetting record);
    
	UserSetting selectSettingByPrimaryKey(Integer id);
	
	List<UserSetting> selectAllSetting();
}