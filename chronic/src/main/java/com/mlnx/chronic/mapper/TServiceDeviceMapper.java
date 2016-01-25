package com.mlnx.chronic.mapper;

import com.mlnx.chronic.entity.TServiceDevice;
import com.mlnx.chronic.vo.ServiceDeviceData;

import java.util.List;

public interface TServiceDeviceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TServiceDevice record);

    TServiceDevice selectByPrimaryKey(Integer id);

    List<TServiceDevice> selectAll();

    int updateByPrimaryKey(TServiceDevice record);

	List<ServiceDeviceData> findServiceByDeviceId();
}