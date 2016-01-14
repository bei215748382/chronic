package com.mlnx.chronic.mapper;

import java.util.List;

import com.mlnx.chronic.entity.TBloodSugarSetting;

public interface TBloodSugarSettingMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(TBloodSugarSetting record);

    TBloodSugarSetting selectByPrimaryKey(Integer id);

    List<TBloodSugarSetting> selectAll();

    int updateByPrimaryKey(TBloodSugarSetting record);
}