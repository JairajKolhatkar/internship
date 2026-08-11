package com.hulkhiretech.payments.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hulkhiretech.payments.constant.Constant;
import com.hulkhiretech.payments.pojo.PaymentResponse;
import com.hulkhiretech.payments.service.interfaces.PaymentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(Constant.PAYMENTS)
@Slf4j
@RequiredArgsConstructor
public class PaymentController {
	
	private final PaymentService paymentService;
	
	@PostMapping("/{id}/expire")
	public PaymentResponse expirePayment(@PathVariable String id) {
		log.info("Expire Payment called| id: {}", id);
		
		PaymentResponse response = paymentService.expirePayment(id);
		log.info("Expire Payment response: {}", response);
		
		return response;
	}
	
}
