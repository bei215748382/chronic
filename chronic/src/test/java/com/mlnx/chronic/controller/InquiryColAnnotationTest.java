package com.mlnx.chronic.controller;


import mlnx.com.http.HttpUtils;
import mlnx.com.http.bean.ApiResponse;
import mlnx.com.http.okhttp.RetrofitCall;

import org.junit.Test;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import com.mlnx.chronic.entity.TProvince;
import com.mlnx.chronic.entity.TQuestion;
import com.mlnx.chronic.mapper.TestBase;

public class InquiryColAnnotationTest extends TestBase{
	
	public interface HttpInquiryTest {
			
		@POST("chronic/inquiry/question")
		Call<ApiResponse<TProvince>> question(@Body TQuestion question);//提问
		
		@GET("chronic/inquiry/history/question")
		Call<ApiResponse<TProvince>> historyQuestion(@Query("keyword") String keyword,@Query("start") Integer start,@Query("end") Integer end);//根据关键字查找咨询历史
		
		@GET("chronic/inquiry/doc/history/question")
		Call<ApiResponse<TProvince>> docHistoryQuestion(@Query("id") Integer id,@Query("start") Integer start,@Query("end") Integer end);//根据医生id查找咨询历史
	}
	
	private RetrofitCall retrofitCall = HttpUtils.Retrofit().baseUrl("http://localhost:8082/").buildRetrofit();
	
	//提问
	@Test
	public void question() {
		// 同步请求
		try {
			TQuestion question = new TQuestion();
			question.setTitle("问题1");
			ApiResponse<TProvince> apiResponse = retrofitCall.call(retrofitCall.conver(HttpInquiryTest.class).question(question));
			System.out.println(apiResponse.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//提问
	@Test
	public void historyQuestion() {
		// 同步请求
		try {
			ApiResponse<TProvince> apiResponse = retrofitCall.call(retrofitCall.conver(HttpInquiryTest.class).historyQuestion("心电",5,10));
			System.out.println(apiResponse.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void docHistoryQuestion() {
		// 同步请求
		try {
			ApiResponse<TProvince> apiResponse = retrofitCall.call(retrofitCall.conver(HttpInquiryTest.class).docHistoryQuestion(1,5,10));
			System.out.println(apiResponse.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
