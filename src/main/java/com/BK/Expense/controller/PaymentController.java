package com.BK.Expense.controller;

import com.BK.Expense.dto.*;
import com.BK.Expense.exception.PaymentException;
import com.BK.Expense.service.IPaymentService;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private IPaymentService paymentService;

    public PaymentController(IPaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<ResultObject> createPayment(@Valid @RequestBody PaymentRequest paymentRequest) throws PaymentException {

        PaymentResponse paymentResponse = paymentService.processPayment(paymentRequest);

        ResultObject resultObject = ResultObject.builder()
                .httpStatus(HttpStatus.CREATED)
                .message("Create payment successfully")
                .isSuccess(true)
                .data(paymentResponse)
                .build();

        return new ResponseEntity(resultObject, HttpStatus.CREATED);

    }
}
