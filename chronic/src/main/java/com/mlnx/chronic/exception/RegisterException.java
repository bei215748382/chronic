package com.mlnx.chronic.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegisterException extends Exception {
	
	private static final Logger log = LoggerFactory
			.getLogger(RegisterException.class);
	
	public RegisterException(Exception e) {
		log.error(String.format("注册异常，事务回滚！error:%s",e.getMessage()));
	}
	
	public RegisterException(Object object) {
		log.error(String.format("error:%s",object.toString()));
	}

}
