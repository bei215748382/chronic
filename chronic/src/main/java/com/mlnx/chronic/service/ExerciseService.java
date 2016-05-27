package com.mlnx.chronic.service;

import com.mlnx.chronic.entity.ExerciseSummery;
import com.mlnx.chronic.entity.TExercise;
import com.mlnx.chronic.entity.TExercisePrescription;
import com.mlnx.chronic.entity.TExerciseType;
import com.mlnx.chronic.util.ApiResponse;

public interface ExerciseService {

	//根据病人id获取运动处方
	ApiResponse<TExercisePrescription> getExercisePrescription(Integer patientID);

	//根据病人id和起始截止时间获取运动记录
	ApiResponse<TExercise> getExercises(Integer patientID, String startTime,
			String stopTime);

	//根据病人id和截止时间获取有限的运动记录
	ApiResponse<TExercise> getLimitExercises(Integer patientID,
			String lastTime, Integer limit);

	//提交运动信息
	ApiResponse<Void> addExercise(TExercise exercise);

	//获取运动类型
	ApiResponse<TExerciseType> getExerciseTypes();

	//获取运动总概况
	ApiResponse<ExerciseSummery> getExerciseSum(Integer patientID);
	
}
