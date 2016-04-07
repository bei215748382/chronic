package com.mlnx.chronic.mapper;

import com.mlnx.chronic.entity.TVisitReport;
import java.util.List;

public interface TVisitReportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TVisitReport record);

    TVisitReport selectByPrimaryKey(Integer id);

    List<TVisitReport> selectAll();

    int updateByPrimaryKey(TVisitReport record);
}