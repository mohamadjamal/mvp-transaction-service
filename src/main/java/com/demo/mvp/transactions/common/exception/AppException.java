package com.demo.mvp.transactions.common.exception;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException {

	private static final long serialVersionUID = -548735617496841175L;

	private final String statusCode;
	private final String errorCode;
	private final String errorDescription;

	public AppException(AppError authError) {
		super(authError.getErrorDescription());
		this.errorCode = authError.getErrorCode();
		this.statusCode = authError.getStatusCode();
		this.errorDescription = authError.getErrorDescription();
	}

}
