package com.mlnx.chronic.mapper;

import java.util.List;

import com.mlnx.chronic.entity.TVisit;
import com.mlnx.chronic.vo.VisitVo;

public interface TVisitMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(TVisit record);

    TVisit selectByPrimaryKey(Integer id);

    List<TVisit> selectAll();

    int updateByPrimaryKey(TVisit record);
    
    List<TVisit> selectAllByDoctorId(Integer id);

	List<VisitVo> search(Integer doctorId);
}