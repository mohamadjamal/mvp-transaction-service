package com.demo.mvp.transactions.common.api;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TransactionDto extends BaseDto {

	private static final long serialVersionUID = -7642991722897767424L;

	private String cardNumber;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date tranDateTime;
	private BigDecimal tranAmount;
	private String tranType;
	private String status;
	private String merchantId;
	private String terminalId;

}
