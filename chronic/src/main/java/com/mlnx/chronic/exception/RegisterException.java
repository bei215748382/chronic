package com.mlnx.chronic.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegisterException extends Exception {
	private static final Logger log = LoggerFactory
			.getLogger(RegisterException.class);
	public RegisterException() {
		log.debug("注册异常，事务回滚！");
	}

}
