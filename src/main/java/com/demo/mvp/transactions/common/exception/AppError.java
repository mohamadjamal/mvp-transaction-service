package com.demo.mvp.transactions.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AppError {

	INVALID_LOGIN_CREDENTIALS("400", "1001", "Invalid login credentials"),
	DATA_NOT_FOUND("404", "1002", "Data not found"),
	INVALID_DATA("400", "1003", "Invalid data"),
	TRANSACTION_NOT_ALLOWED("404", "1004", "Transaction not allowed"),
	SERVER_ERROR("500", "1500", "Unable to process your request, Please try again later, if problem persist, contact sys admin");

	private String statusCode;
	private String errorCode;
	private String errorDescription;
}
