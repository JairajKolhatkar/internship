package com.hulkhiretech.payments.service.impl;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hulkhiretech.payments.constant.ErrorCodeEnum;
import com.hulkhiretech.payments.exception.StripeProviderException;
import com.hulkhiretech.payments.http.HttpRequest;
import com.hulkhiretech.payments.http.HttpServiceEngine;
import com.hulkhiretech.payments.pojo.CreatePaymentRequest;
import com.hulkhiretech.payments.pojo.PaymentResponse;
import com.hulkhiretech.payments.service.helper.CreatePaymentHelper;
import com.hulkhiretech.payments.service.interfaces.PaymentService;
import com.hulkhiretech.payments.util.JsonUtil;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

	private final HttpServiceEngine httpServiceEngine;

	private final CreatePaymentHelper createPaymentHelper;

	private final ChatClient chatClient;

	@Override
	public PaymentResponse createPayment(CreatePaymentRequest createPaymentRequest) {
		log.info("Processing payment creation|| "
				+ "createPaymentRequest: {}", createPaymentRequest);

		// if createPaymementRequest 1st line item quantity is 0 or less then throw exception
		if (createPaymentRequest.getLineItems().get(0).getQuantity() <= 0) {
			throw new StripeProviderException(
					ErrorCodeEnum.INVALID_QUANTITY.getErrorCode(),
					ErrorCodeEnum.INVALID_QUANTITY.getErrorMessage(),
					HttpStatus.BAD_REQUEST
					);
		}

		HttpRequest httpRequest = createPaymentHelper.prepareHttpRequest(
				createPaymentRequest);
		log.info("Prepared HttpRequest: {}", httpRequest);

		ResponseEntity<String> httpResponse = httpServiceEngine.makeHttpCall(httpRequest);
		log.info("HTTP Service response: {}", httpResponse);

		PaymentResponse response = createPaymentHelper.processResponse(httpResponse);
		log.info("Final PaymentResponse to be returned: {}", response);

		return response;
	}




}
