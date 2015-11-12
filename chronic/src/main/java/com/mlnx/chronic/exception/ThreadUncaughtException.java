package com.mlnx.chronic.exception;

public class ThreadUncaughtException implements Thread.UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println(String.format("ThreadUncaughtException---caught %s", e));
	}

}
