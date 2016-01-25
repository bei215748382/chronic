package com.mlnx.chronic.mapper;

import java.util.List;

import com.mlnx.chronic.entity.TService;
import com.mlnx.chronic.vo.ServiceAddressData;
import com.mlnx.chronic.vo.ServiceData;
import com.mlnx.chronic.vo.ServiceVo;

public interface TServiceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TService record);

    TService selectByPrimaryKey(Integer id);

    List<TService> selectAll();

    int updateByPrimaryKey(TService record);

	List<ServiceVo> selectAllService();

	List<ServiceData> findAllServiceGroupByDoctorId();

	List<ServiceAddressData> findAllServiceGroupByAddressId();
}