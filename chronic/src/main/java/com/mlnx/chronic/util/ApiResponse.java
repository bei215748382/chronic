package com.mlnx.chronic.util;

import java.util.List;

public class ApiResponse<T> {

	private String responseCode; // 返回码，0为成功
	private String msg; // 返回信息
	private T obj; // 单个对象
	private List<T> objList; // 数组对象
	private int currentPage; // 当前页数
	private int pageSize; // 每页显示数量
	private int maxCount; // 总条数
	private int maxPage; // 总页数

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

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(int maxCount) {
		this.maxCount = maxCount;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

}
