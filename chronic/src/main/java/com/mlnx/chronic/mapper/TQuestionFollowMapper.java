package com.mlnx.chronic.mapper;

import com.mlnx.chronic.entity.TQuestionFollow;
import java.util.List;

public interface TQuestionFollowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TQuestionFollow record);

    TQuestionFollow selectByPrimaryKey(Integer id);

    List<TQuestionFollow> selectAll();

    int updateByPrimaryKey(TQuestionFollow record);
}