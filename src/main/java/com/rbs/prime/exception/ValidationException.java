package com.rbs.prime.exception;

public class ValidationException extends Exception {

	private static final long serialVersionUID = 7441683465635919264L;
	private String message;

	public ValidationException(long maxLimit) {
		message = "Prime number max limit less than zero: " + maxLimit;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

}
