package com.mlnx.springmvc.entity;

import java.util.List;


public class Data{
	private int total;
	private int pageSize;
	private List<Patient> rows;
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<Patient> getRows() {
		return rows;
	}
	public void setRows(List<Patient> rows) {
		this.rows = rows;
	}
	@Override
	public String toString() {
		return "Data [total=" + total + ", pageSize=" + pageSize + ", rows="
				+ rows + "]";
	}
	
}