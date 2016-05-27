package com.mlnx.springmvc.entity;


public 	class Result {
	private String status;
	private Data results;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Data getResults() {
		return results;
	}
	public void setResults(Data results) {
		this.results = results;
	}
	@Override
	public String toString() {
		return "Result [status=" + status + ", results=" + results + "]";
	}
	
}
