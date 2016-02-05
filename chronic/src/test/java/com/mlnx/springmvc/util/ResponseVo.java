package com.mlnx.springmvc.util;

import java.util.List;

public class ResponseVo<T> {
	
	private String responseCode;
	
	private String msg;
	
	private T obj;
	
	private List<T> objList;

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}

	public List<T> getObjList() {
		return objList;
	}

	public void setObjList(List<T> objList) {
		this.objList = objList;
	}

	@Override
	public String toString() {
		return "ResponseVo [responseCode=" + responseCode + ", msg=" + msg
				+ ", obj=" + obj + ", objList=" + objList + "]";
	}

}
