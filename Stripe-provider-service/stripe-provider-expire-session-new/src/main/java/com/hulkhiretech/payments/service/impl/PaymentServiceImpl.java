package com.hulkhiretech.payments.service.impl;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hulkhiretech.payments.http.HttpRequest;
import com.hulkhiretech.payments.http.HttpServiceEngine;
import com.hulkhiretech.payments.pojo.PaymentResponse;
import com.hulkhiretech.payments.service.helper.ExpirePaymentHelper;
import com.hulkhiretech.payments.service.interfaces.PaymentService;
import com.hulkhiretech.payments.stripe.StripeResponse;
import com.hulkhiretech.payments.util.StripeResponseUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

	private final HttpServiceEngine httpServiceEngine;

	private final ExpirePaymentHelper expirePaymentHelper;

	private final ChatClient chatClient;

	@Override
	public PaymentResponse expirePayment(String id) {
		log.info("Expire Payment called| id: {}", id);

		HttpRequest httpRequest = expirePaymentHelper.prepareHttpRequest(id);
		log.info("Prepared HttpRequest: {}", httpRequest);

		ResponseEntity<String> httpResponse = httpServiceEngine.makeHttpCall(httpRequest);
		log.info("HTTP Service response: {}", httpResponse);

		StripeResponse stripeResponse = expirePaymentHelper.processResponse(httpResponse);
		log.info("Final PaymentResponse to be returned: {}", stripeResponse);

		PaymentResponse paymentRes = StripeResponseUtil.preparePaymentResponse(
				stripeResponse);
		log.info("PaymentResponse constructed: {}", paymentRes);

		return paymentRes;
	}

}
