package com.BK.Expense.service;

import com.BK.Expense.dto.PaymentRequest;
import com.BK.Expense.dto.PaymentResponse;
import com.BK.Expense.exception.PaymentException;

public interface IPaymentService {
    PaymentResponse processPayment(PaymentRequest paymentRequest) throws PaymentException;
}
