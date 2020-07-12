package com.demo.mvp.transactions.common.dto;

import java.io.Serializable;

import com.demo.mvp.transactions.common.exception.AppException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class ExceptionResponse implements Serializable {

	private static final long serialVersionUID = 198490239267152921L;

	private String statusCode;
	private String errorCode;
	private String errorDescription;

	public ExceptionResponse(AppException authException) {
		this.errorCode = authException.getErrorCode();
		this.statusCode = authException.getStatusCode();
		this.errorDescription = authException.getErrorDescription();
	}

}
