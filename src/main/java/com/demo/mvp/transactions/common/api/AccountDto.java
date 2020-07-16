package com.demo.mvp.transactions.common.api;

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
public class AccountDto extends BaseDto {

	private static final long serialVersionUID = -7642991722897767424L;

	private String accountNumber;
	private String firstName;
	private String lastName;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dob;
	private String address1;
	private String address2;
	private String city;
	private String region;
	private String country;
	private String zip;

}
