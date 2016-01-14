package com.mlnx.chronic.util;

public class ChronicResponse {
	
	private String responseCode;
	
	private String msg;

	public ChronicResponse(){
		
	}
	
	public ChronicResponse(String code,String msg){
		this.responseCode = code;
		this.msg = msg;
	}
	
	public ChronicResponse(EnumCollection.ResponseCode response){
		this.responseCode = response.getCode();
		this.msg = response.getMsg();
	}
	
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

	@Override
	public String toString() {
		return "Response [responseCode=" + responseCode + ", msg=" + msg + "]";
	}

}
