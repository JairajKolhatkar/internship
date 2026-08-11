package com.hulkhiretech.payments.service.interfaces;

import com.hulkhiretech.payments.pojo.PaymentResponse;

public interface PaymentService {
	
	public PaymentResponse expirePayment(String id);

}

