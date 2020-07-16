package com.demo.mvp.transactions.endpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

	@GetMapping("/")
	public String getTransaction() {
		return "Simple service is up and running";
	}
}