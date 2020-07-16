package com.demo.mvp.transactions.common.advice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.demo.mvp.transactions.common.dto.ExceptionResponse;
import com.demo.mvp.transactions.common.exception.AppError;
import com.demo.mvp.transactions.common.exception.AppException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { AppException.class })
	protected ExceptionResponse appExcpetion(AppException ex) {
		log.error("App Exception : ", ex);
		return new ExceptionResponse(ex);
	}

	@ExceptionHandler(value = { Exception.class })
	protected ExceptionResponse handleExcpetion(Exception ex) {
		log.error("Unhandled Exception : ", ex);
		return new ExceptionResponse(new AppException(AppError.SERVER_ERROR));
	}

}
