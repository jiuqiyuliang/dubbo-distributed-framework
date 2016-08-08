package com.dmsd.itoo.base.exception;

public class OptimisticLockingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public OptimisticLockingException(String message) {
		super(message);
	}
}