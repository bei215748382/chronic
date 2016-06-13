package com.mlnx.chronic.mapper;

import com.mlnx.chronic.entity.TQuestion;

import java.util.List;
import java.util.Map;

public interface TQuestionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TQuestion record);

    TQuestion selectByPrimaryKey(Integer id);

    List<TQuestion> selectAll();

    int updateByPrimaryKey(TQuestion record);

	List<TQuestion> historyQuestion(Map<String, Object> paramMap);

	List<TQuestion> docHistoryQuestion(Map<String, Object> paramMap);
}