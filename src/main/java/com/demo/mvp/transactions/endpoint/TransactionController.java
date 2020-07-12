package com.demo.mvp.transactions.endpoint;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.mvp.transactions.common.api.TransactionDto;
import com.demo.mvp.transactions.entity.Transaction;
import com.demo.mvp.transactions.service.TransactionService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private DozerBeanMapper dozerMapper;

	private Function<Transaction, TransactionDto> transactionToDtoFunction = transaction -> dozerMapper.map(transaction,
			TransactionDto.class);

	private Function<TransactionDto, Transaction> transactionDtoToEntityFunction = transactionDto -> dozerMapper
			.map(transactionDto, Transaction.class);

	@PostMapping("transactions")
	public TransactionDto saveTransaction(@RequestBody final TransactionDto transactionDto) {
		log.debug("TransactionController::saveTransaction");
		Transaction transaction = transactionDtoToEntityFunction.apply(transactionDto);
		transaction = transactionService.saveTransaction(transaction);
		return transactionToDtoFunction.apply(transaction);
	}

	@GetMapping("transactions/{transactionId}")
	public TransactionDto getTransaction(@PathVariable(value = "transactionId") final String transactionId) {
		log.debug("TransactionController::getTransaction by transactionId");
		Transaction transaction = transactionService.getTransactionsById(transactionId);
		return transactionToDtoFunction.apply(transaction);
	}

	@GetMapping("transactions")
	public List<TransactionDto> listTransactions() {
		log.debug("TransactionController::listTransactions");
		List<Transaction> transactions = transactionService.listTransactions();
		return transactions.stream().map(transactionToDtoFunction).collect(Collectors.toList());
	}

	@GetMapping("transactions/merchants/{merchantId}")
	public List<TransactionDto> listTransactionsByMerchantId(
			@PathVariable(value = "merchantId") final String merchantId) {
		log.debug("TransactionController::listTransactionsByMerchantId");
		List<Transaction> transactions = transactionService.listTransactionsByMerchantId(merchantId);
		return transactions.stream().map(transactionToDtoFunction).collect(Collectors.toList());
	}

	@GetMapping("transactions/cards/{cardNumber}")
	public List<TransactionDto> listTransactionsByCardNumber(
			@PathVariable(value = "cardNumber") final String cardNumber) {
		log.debug("TransactionController::listTransactionsByCardNumber");
		List<Transaction> transactions = transactionService.listTransactionsByCardNumber(cardNumber);
		return transactions.stream().map(transactionToDtoFunction).collect(Collectors.toList());
	}

}