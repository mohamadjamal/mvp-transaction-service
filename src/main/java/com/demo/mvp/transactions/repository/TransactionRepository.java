package com.demo.mvp.transactions.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.mvp.transactions.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, String> {

	List<Transaction> findByMerchantId(final String merchantId);

	List<Transaction> findByCardNumber(final String cardNumber);

}
