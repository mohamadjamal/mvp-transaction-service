package com.demo.mvp.transactions.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.demo.mvp.transactions.common.entity.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TRANSACTIONS")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Transaction extends BaseEntity {

	private static final long serialVersionUID = -7642991722897767424L;

	@Column(name = "CARD_NUMBER")
	private String cardNumber;

	@Column(name = "TRAN_DATE_TIME")
	private Date tranDateTime;

	@Column(name = "TRAN_AMT")
	private BigDecimal tranAmount;

	@Column(name = "TRAN_TYPE")
	private String tranType;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "MERCHANT_ID")
	private String merchantId;

	@Column(name = "TERMINAL_ID")
	private String terminalId;

}
