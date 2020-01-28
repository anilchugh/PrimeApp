package com.rbs.prime.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rbs.prime.exception.ValidationException;
import com.rbs.prime.model.ApiErrorResponse;

@RestControllerAdvice
public class PrimeExceptionHandler {

	@ExceptionHandler(value = { ValidationException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrorResponse handleException(ValidationException exception) {
		return new ApiErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
	}

}
