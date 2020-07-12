package com.demo.mvp.transactions.client;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.demo.mvp.transactions.common.api.CardDto;
import com.demo.mvp.transactions.common.api.TransactionDto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CardServiceClient {

	@Autowired
	@Qualifier("cardServiceTemplate")
	private RestTemplate restTemplate;

	private final String authCardTransactionURL;

	public CardServiceClient(@Value("${com.demo.mvp.cards.endpoint}") final String endpoint,
			@Value("${com.demo.mvp.cards.authCardTransactionUrl}") final String authCardTransactionPath) {
		this.authCardTransactionURL = new StringBuilder(endpoint).append(authCardTransactionPath).toString();
	}

	@HystrixCommand(commandKey = "cardServiceCommand", threadPoolKey = "cardServiceClient", fallbackMethod = "authCardTransactionFallback")
	public CardDto authCardTransaction(final String cardNumber, final TransactionDto transactionDto) {
		final HttpHeaders httpHeaders = new HttpHeaders();
		final Map<String, String> pathVariableMap = new HashMap<>();
		pathVariableMap.put("cardNumber", cardNumber);
		final HttpEntity<TransactionDto> entityReq = new HttpEntity<>(transactionDto, httpHeaders);
		final ResponseEntity<CardDto> responseEntity = this.restTemplate.exchange(this.authCardTransactionURL,
				HttpMethod.PUT, entityReq, CardDto.class, pathVariableMap);
		return responseEntity.getBody();
	}

	public CardDto authCardTransactionFallback(final String cardNumber, final TransactionDto transactionDto,
			final Throwable exThrowable) {
		log.error("HyxtrixFallBack - fallback - From: TransactionService, To: CardService( {} ), dueTo: {} : {}",
				"authCardTransaction", exThrowable.getClass().getName(),
				(exThrowable.getMessage() == null ? exThrowable.getClass().getName() : exThrowable.getMessage()));
		return null;
	}
}
