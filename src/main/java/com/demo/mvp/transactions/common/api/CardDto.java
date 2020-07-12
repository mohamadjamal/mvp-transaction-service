package com.demo.mvp.transactions.common.api;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CardDto extends BaseDto {

	private static final long serialVersionUID = -7642991722897767424L;

	private String cardNumber;
	private String expMonth;
	private String expYear;
	private String blocked;
	private String hotlisted;
	private BigDecimal maxLimit;
	private BigDecimal availableAmt;
	private String accountNumber;

}
