package com.mlnx.chronic.mapper;

import java.util.List;

import com.mlnx.chronic.entity.TFeedback;

public interface TFeedbackMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(TFeedback record);

    TFeedback selectByPrimaryKey(Integer id);

    List<TFeedback> selectAll();

    int updateByPrimaryKey(TFeedback record);
}