package com.mlnx.chronic.mapper;

import com.mlnx.chronic.entity.TExerciseType;

import java.util.List;

public interface TExerciseTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TExerciseType record);

    TExerciseType selectByPrimaryKey(Integer id);

    List<TExerciseType> selectAll();

    int updateByPrimaryKey(TExerciseType record);

}