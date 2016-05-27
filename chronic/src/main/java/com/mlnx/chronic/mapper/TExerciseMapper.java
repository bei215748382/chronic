package com.mlnx.chronic.mapper;

import com.mlnx.chronic.entity.TExercise;

import java.util.List;
import java.util.Map;

public interface TExerciseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TExercise record);

    TExercise selectByPrimaryKey(Integer id);

    List<TExercise> selectAll();

    int updateByPrimaryKey(TExercise record);

	List<TExercise> getExercises(Map<String, Object> param);//根据起始截止时间获取运动信息

	List<TExercise> getLimitExercises(Map<String, Object> param);//根据限制数量获取运动信息

	Integer getTotalExerciseCount(Integer patientId);//病人总共运动的天数

	float getTotalDistance(Integer patientId);//病人总共运动的距离
}