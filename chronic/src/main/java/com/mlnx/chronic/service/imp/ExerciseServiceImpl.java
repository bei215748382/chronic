package com.mlnx.chronic.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mlnx.chronic.entity.ExerciseSummery;
import com.mlnx.chronic.entity.TExercise;
import com.mlnx.chronic.entity.TExercisePrescription;
import com.mlnx.chronic.entity.TExerciseType;
import com.mlnx.chronic.mapper.TExerciseMapper;
import com.mlnx.chronic.mapper.TExercisePrescriptionMapper;
import com.mlnx.chronic.mapper.TExerciseTypeMapper;
import com.mlnx.chronic.service.ExerciseService;
import com.mlnx.chronic.util.ApiResponse;
import com.mlnx.chronic.util.EnumCollection.ResponseCode;

@Service
public class ExerciseServiceImpl implements ExerciseService {

	@Autowired
	private TExercisePrescriptionMapper exercisePrescriptionMapper;
	
	@Autowired
	private TExerciseMapper exerciseMapper;
	
	@Autowired
	private TExerciseTypeMapper exerciseTypeMapper;
	
	@Override
	public ApiResponse<TExercisePrescription> getExercisePrescription(
			Integer patientId) {
		ApiResponse<TExercisePrescription> apir = new ApiResponse<TExercisePrescription>();
		try{
			List<TExercisePrescription> list = exercisePrescriptionMapper.selectByPatientId(patientId);
			apir.setObjList(list);
			apir.setResponseCode(ResponseCode.GET_EXERCISE_P_CODE_SUCESS.getCode());
			apir.setMsg(ResponseCode.GET_EXERCISE_P_CODE_SUCESS.getMsg());
		} catch(Exception e){
			e.printStackTrace();
			apir.setResponseCode(ResponseCode.GET_EXERCISE_P_CODE_FAIL.getCode());
			apir.setMsg(ResponseCode.GET_EXERCISE_P_CODE_FAIL.getMsg());
		}
		return apir;
	}

	@Override
	public ApiResponse<TExercise> getExercises(Integer patientID,
			String startTime, String stopTime) {
		ApiResponse<TExercise> apir = new ApiResponse<TExercise>();
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("patientId", patientID);
		param.put("start", startTime);
		param.put("end", stopTime);
		try{
			List<TExercise> list = exerciseMapper.getExercises(param);
			apir.setObjList(list);
			apir.setResponseCode(ResponseCode.GET_EXERCISE_CODE_SUCESS.getCode());
			apir.setMsg(ResponseCode.GET_EXERCISE_CODE_SUCESS.getMsg());
		} catch(Exception e){
			e.printStackTrace();
			apir.setResponseCode(ResponseCode.GET_EXERCISE_CODE_FAIL.getCode());
			apir.setMsg(ResponseCode.GET_EXERCISE_CODE_FAIL.getMsg());
		}
		return apir;
	}

	@Override
	public ApiResponse<TExercise> getLimitExercises(Integer patientID,
			String lastTime, Integer limit) {
		ApiResponse<TExercise> apir = new ApiResponse<TExercise>();
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("patientId", patientID);
		param.put("limit", limit);
		param.put("end", lastTime);
		try{
			List<TExercise> list = exerciseMapper.getLimitExercises(param);
			apir.setObjList(list);
			apir.setResponseCode(ResponseCode.GET_EXERCISE_CODE_SUCESS.getCode());
			apir.setMsg(ResponseCode.GET_EXERCISE_CODE_SUCESS.getMsg());
		} catch(Exception e){
			e.printStackTrace();
			apir.setResponseCode(ResponseCode.GET_EXERCISE_CODE_FAIL.getCode());
			apir.setMsg(ResponseCode.GET_EXERCISE_CODE_FAIL.getMsg());
		}
		return apir;
	}

	@Override
	public ApiResponse<Void> addExercise(TExercise exercise) {
		ApiResponse<Void> apir = new ApiResponse<Void>();
		try{
			exerciseMapper.insert(exercise);
			apir.setResponseCode(ResponseCode.SAVE_EXERCISE_CODE_SUCESS.getCode());
			apir.setMsg(ResponseCode.SAVE_EXERCISE_CODE_SUCESS.getMsg());
		} catch(Exception e){
			e.printStackTrace();
			apir.setResponseCode(ResponseCode.SAVE_EXERCISE_CODE_FAIL.getCode());
			apir.setMsg(ResponseCode.SAVE_EXERCISE_CODE_FAIL.getMsg());
		}
		return apir;
	}

	@Override
	public ApiResponse<TExerciseType> getExerciseTypes() {
		ApiResponse<TExerciseType> apir = new ApiResponse<TExerciseType>();
		try{
			List<TExerciseType> list = exerciseTypeMapper.selectAll();
			apir.setObjList(list);
			apir.setResponseCode(ResponseCode.GET_EXERCISE_TYPE_CODE_SUCESS.getCode());
			apir.setMsg(ResponseCode.GET_EXERCISE_TYPE_CODE_SUCESS.getMsg());
		} catch(Exception e){
			e.printStackTrace();
			apir.setResponseCode(ResponseCode.GET_EXERCISE_TYPE_CODE_FAIL.getCode());
			apir.setMsg(ResponseCode.GET_EXERCISE_TYPE_CODE_FAIL.getMsg());
		}
		return apir;
	}

	@Override
	public ApiResponse<ExerciseSummery> getExerciseSum(Integer patientId) {
		ApiResponse<ExerciseSummery> apir = new ApiResponse<ExerciseSummery>();
		try{
			Integer count = exerciseMapper.getTotalExerciseCount(patientId);
			float totalDistance = exerciseMapper.getTotalDistance(patientId);
			ExerciseSummery sum = new ExerciseSummery();
			sum.setTotalDistance(totalDistance);
			sum.setTotalExerciseCount(count);
			apir.setObj(sum);
			apir.setResponseCode(ResponseCode.GET_EXERCISE_SUMMERY_CODE_SUCESS.getCode());
			apir.setMsg(ResponseCode.GET_EXERCISE_SUMMERY_CODE_SUCESS.getMsg());
		} catch(Exception e){
			e.printStackTrace();
			apir.setResponseCode(ResponseCode.GET_EXERCISE_SUMMERY_CODE_FAIL.getCode());
			apir.setMsg(ResponseCode.GET_EXERCISE_SUMMERY_CODE_FAIL.getMsg());
		}
		return apir;
	}

}
