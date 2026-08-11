package com.hulkhiretech.payments.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/{id}")
	public PaymentResponse getPayment(@PathVariable String id) {
		log.info("Get Payment called| id: {}", id);
		
		PaymentResponse response = paymentService.getPayment(id);
		log.info("Get Payment response: {}", response);
		
		return response;
	}
	
}
