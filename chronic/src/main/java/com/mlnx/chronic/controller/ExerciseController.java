package com.mlnx.chronic.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.mlnx.chronic.entity.ExerciseSummery;
import com.mlnx.chronic.entity.TExercise;
import com.mlnx.chronic.entity.TExercisePrescription;
import com.mlnx.chronic.entity.TExerciseType;
import com.mlnx.chronic.service.ExerciseService;
import com.mlnx.chronic.util.ApiResponse;
import com.mlnx.chronic.util.EnumCollection.ResponseCode;

@Controller
@RequestMapping("/exercise")
public class ExerciseController {
	
	@Autowired
	private ExerciseService exerciseService;

	@RequestMapping(value = "/getExercisePrescription", method = RequestMethod.GET)
	@ResponseBody
	public ApiResponse<TExercisePrescription> getExercisePrescription(Integer patientID) {
		return exerciseService.getExercisePrescription(patientID);
		
//		ApiResponse<TExerciseprescription> apiResponse = new ApiResponse<TExerciseprescription>();
//		apiResponse.setResponseCode(ResponseCode.GET_EXERCISE_P_CODE_SUCESS.getCode());
//		apiResponse.setMsg(ResponseCode.GET_EXERCISE_P_CODE_SUCESS.getMsg());
//
//		try {
//			TExerciseprescription exercisePrescriptions = DbxUtils.findFrist(ExercisePrescription.class,
//					new Selector(WhereBuilder.b("patientID", "=", patientID)).orderBy("exercisePrescriptionTime",
//							true));
//			apiResponse.setObj(exercisePrescriptions);
//		} catch (DbxUtilException e) {
//			e.printStackTrace();
//			apiResponse.setResponseCode(ResponseCode.GET_EXERCISE_P_CODE_FAIL.getCode());
//			apiResponse.setMsg("数据库中读取运动处方失败");
//		}
//
//		return apiResponse;
	}

	/**
	 * 根据起始时间获取运动信息
	 * 
	 * @param patientID
	 * @param startTime
	 * @param stopTime
	 * @return
	 */
	@RequestMapping(value = "/getExercises", method = RequestMethod.GET)
	@ResponseBody
	public ApiResponse<TExercise> getExercises(Integer patientID, String startTime, String stopTime) {
		return exerciseService.getExercises(patientID, startTime, stopTime);
//		ApiResponse<Exercise> apiResponse = new ApiResponse<Exercise>();
//		apiResponse.setResponseCode(ResponseCode.GET_EXERCISE_CODE_SUCESS.getCode());
//		apiResponse.setMsg(ResponseCode.GET_EXERCISE_CODE_SUCESS.getMsg());
//
//		try {
//			List<Exercise> exercises = DbxUtils
//					.findAll(Exercise.class,
//							new Selector(WhereBuilder.b("patientID", "=", patientID)
//									.and("startExerciseTime", ">=", startTime).and("stopExerciseTime", "<=", stopTime))
//											.orderBy("startExerciseTime", true));
//			apiResponse.setObjList(exercises);
//		} catch (DbxUtilException e) {
//			e.printStackTrace();
//			apiResponse.setResponseCode(ResponseCode.GET_EXERCISE_CODE_FAIL.getCode());
//			apiResponse.setMsg("数据库中读取运动信息失败");
//		}
//		return apiResponse;
	}

	/**
	 * 根据限制数量获取运动信息
	 * 
	 * @param patientID
	 * @param lastTime
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/getLimitExercises", method = RequestMethod.GET)
	@ResponseBody
	public ApiResponse<TExercise> getLimitExercises(Integer patientID, String lastTime, Integer limit) {
		return exerciseService.getLimitExercises(patientID, lastTime, limit);
//		ApiResponse<Exercise> apiResponse = new ApiResponse<Exercise>();
//		apiResponse.setResponseCode(ResponseCode.GET_EXERCISE_CODE_SUCESS.getCode());
//		apiResponse.setMsg(ResponseCode.GET_EXERCISE_CODE_SUCESS.getMsg());
//
//		try {
//			System.out.println("lastTime：" + lastTime);
//			List<Exercise> exercises = DbxUtils
//					.findAll(Exercise.class,
//							new Selector(
//									WhereBuilder.b("patientID", "=", patientID).and("stopExerciseTime", "<=", lastTime))
//											.orderBy("startExerciseTime", true).limit(limit));
//			apiResponse.setObjList(exercises);
//		} catch (DbxUtilException e) {
//			e.printStackTrace();
//			apiResponse.setResponseCode(ResponseCode.GET_EXERCISE_CODE_FAIL.getCode());
//			apiResponse.setMsg("数据库中读取运动信息失败");
//		}
//		return apiResponse;
	}

	/**
	 * 提交运动信息
	 * 
	 * @param string
	 * @return
	 */
	@RequestMapping(value = "/addExercise", method = RequestMethod.POST)
	@ResponseBody
	public ApiResponse<Void> addExercise(@RequestBody TExercise exercise) {
		System.out.println(exercise.toString());
		return exerciseService.addExercise(exercise);
//		Exercise exercise = JSON.parseObject(string, Exercise.class);
//		System.out.println(exercise);
//
//		ApiResponse<Void> apiResponse = new ApiResponse<Void>();
//		apiResponse.setResponseCode(ResponseCode.SAVE_EXERCISE_CODE_SUCESS.getCode());
//		apiResponse.setMsg(ResponseCode.SAVE_EXERCISE_CODE_SUCESS.getMsg());
//		try {
//			DbxUtils.save(exercise);
//		} catch (DbxUtilException e) {
//			e.printStackTrace();
//			apiResponse.setResponseCode(ResponseCode.SAVE_EXERCISE_CODE_FAIL.getCode());
//			apiResponse.setMsg("数据库中保存运动信息失败");
//		}
//		return apiResponse;
	}

	/**
	 * 获取运动类型
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getExerciseTypes", method = RequestMethod.GET)
	@ResponseBody
	public ApiResponse<TExerciseType> getExerciseTypes() {
		return exerciseService.getExerciseTypes();
//		ApiResponse<ExerciseType> apiResponse = new ApiResponse<ExerciseType>();
//		apiResponse.setResponseCode(ResponseCode.GET_EXERCISE_TYPE_CODE_SUCESS.getCode());
//		apiResponse.setMsg(ResponseCode.GET_EXERCISE_TYPE_CODE_SUCESS.getMsg());
//
//		try {
//			List<ExerciseType> exercises = DbxUtils.findAll(ExerciseType.class, new Selector(WhereBuilder.b()));
//			apiResponse.setObjList(exercises);
//		} catch (DbxUtilException e) {
//			e.printStackTrace();
//			apiResponse.setResponseCode(ResponseCode.GET_EXERCISE_TYPE_CODE_FAIL.getCode());
//			apiResponse.setMsg("数据库中读取运动类型失败");
//		}
//		return apiResponse;
	}

	/**
	 * 获取运动总概况
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getExerciseSum", method = RequestMethod.GET)
	@ResponseBody
	public ApiResponse<ExerciseSummery> getExerciseSum(Integer patientID) {
		return exerciseService.getExerciseSum(patientID);
//		ApiResponse<ExerciseSummery> apiResponse = new ApiResponse<ExerciseSummery>();
//		apiResponse.setResponseCode(ResponseCode.GET_EXERCISE_SUMMERY_CODE_SUCESS.getCode());
//		apiResponse.setMsg(ResponseCode.GET_EXERCISE_SUMMERY_CODE_SUCESS.getMsg());
//
//		ExerciseSummery exerciseSummery = new ExerciseSummery();
//
//		try {
//			List<Map> maps = DbxUtils
//					.execQuery("select DATE_FORMAT(startexercisetime, '%Y-%m-%d'), count(*) as daygroup "
//							+ "from exercise where patientID=" + patientID
//							+ " group by DATE_FORMAT(startexercisetime, '%Y-%m-%d')");
//			if (maps != null)
//				exerciseSummery.setTotalExerciseCount(maps.size());
//
//			maps = DbxUtils.execQuery(
//					"select SUM(motiondistance) AS totalmotiondistance from exercise where patientID=" + patientID);
//			if (maps != null && maps.size() > 0){
//				Double d = (Double) maps.get(0).get("totalmotiondistance");
//				exerciseSummery.setTotalDistance(d.floatValue());
//			}
//			apiResponse.setObj(exerciseSummery);
//				
//		} catch (DbxUtilException e) {
//			e.printStackTrace();
//			apiResponse.setResponseCode(ResponseCode.GET_EXERCISE_SUMMERY_CODE_FAIL.getCode());
//			apiResponse.setMsg("数据库中读取运动总信息失败");
//		}
//		return apiResponse;
	}

}
