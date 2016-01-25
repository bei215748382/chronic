package com.mlnx.chronic.mapper;

import java.util.List;

import com.mlnx.chronic.entity.TDevice;
import com.mlnx.chronic.vo.DeviceVo;

public interface TDeviceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TDevice record);

    TDevice selectByPrimaryKey(Integer id);

    List<TDevice> selectAll();

    int updateByPrimaryKey(TDevice record);

	DeviceVo findDeviceById(Integer id);
}