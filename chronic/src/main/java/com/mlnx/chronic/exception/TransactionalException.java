package com.mlnx.chronic.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransactionalException extends Exception {
	private static final Logger log = LoggerFactory
			.getLogger(TransactionalException.class);

	public TransactionalException() {
		log.debug("事务异常");
	}

	public TransactionalException(String msg) {
		log.debug(msg);
	}

}
