package com.demo.mvp.transactions.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.mvp.transactions.client.CardServiceClient;
import com.demo.mvp.transactions.common.api.CardDto;
import com.demo.mvp.transactions.common.api.TransactionDto;
import com.demo.mvp.transactions.common.exception.AppError;
import com.demo.mvp.transactions.common.exception.AppException;
import com.demo.mvp.transactions.entity.Transaction;
import com.demo.mvp.transactions.repository.TransactionRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private CardServiceClient cardServiceClient;

	@Autowired
	private DozerBeanMapper dozerMapper;

	private Function<Transaction, TransactionDto> transactionToDtoFunction = transaction -> dozerMapper.map(transaction,
			TransactionDto.class);

	public Transaction saveTransaction(final Transaction transaction) {
		CardDto cardDto = cardServiceClient.authCardTransaction(transaction.getCardNumber(),
				transactionToDtoFunction.apply(transaction));
		if (null == cardDto || StringUtils.isBlank(cardDto.getCardNumber())) {
			transaction.setStatus("FAILED");
		}
		transactionRepository.save(transaction);
		return transaction;
	}

	public List<Transaction> listTransactions() {
		log.info("List all transactions");
		return transactionRepository.findAll();
	}

	public List<Transaction> listTransactionsByCardNumber(final String cardNumber) {
		log.info("List transactions by cardNumber : {}", cardNumber);
		return transactionRepository.findByCardNumber(cardNumber);
	}

	public List<Transaction> listTransactionsByMerchantId(final String merchantId) {
		log.info("List transactions of merchant id : {}", merchantId);
		return transactionRepository.findByMerchantId(merchantId);
	}

	public Transaction getTransactionsById(final String transactionId) {
		log.info("Get Transaction by id : {}", transactionId);
		final Optional<Transaction> transactionOptional = transactionRepository.findById(transactionId);
		return transactionOptional.orElseThrow(() -> new AppException(AppError.DATA_NOT_FOUND));
	}

}
