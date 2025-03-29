package com.BK.Expense.service.Impl;

import com.BK.Expense.dto.PaymentRequest;
import com.BK.Expense.dto.PaymentResponse;
import com.BK.Expense.entity.ExpenseRequest;
import com.BK.Expense.entity.Payment;
import com.BK.Expense.enums.PaymentMethodEnum;
import com.BK.Expense.exception.PaymentException;
import com.BK.Expense.repository.PaymentRepository;
import com.BK.Expense.service.IPaymentService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements IPaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);
    private ModelMapper modelMapper;
    private PaymentRepository paymentRepository;

    public PaymentServiceImpl(ModelMapper modelMapper, PaymentRepository paymentRepository) {
        this.modelMapper = modelMapper;
        this.paymentRepository = paymentRepository;
    }

    @Override
    @Transactional
    public PaymentResponse processPayment(PaymentRequest paymentRequest) throws PaymentException {
        ExpenseRequest expenseRequest = new ExpenseRequest();
        expenseRequest.setId(paymentRequest.getExpenseId());

        Payment newPayment = Payment.builder()
                .paymentMethod(PaymentMethodEnum.BANK)
                .amount(paymentRequest.getAmount())
                .expenseRequest(expenseRequest)
                .build();
        try {
            Payment savedPayment = paymentRepository.save(newPayment);


            PaymentResponse paymentResponse = PaymentResponse.builder()
                    .id(savedPayment.getId())
                    .requestName(savedPayment.getExpenseRequest().getName())
                    .amount(savedPayment.getAmount())
                    .createdAt(savedPayment.getCreatedAt())
                    .createdBy(savedPayment.getCreatedBy())
                    .paymentMethod(savedPayment.getPaymentMethod())
                    .isSuccess(true)
                    .build();
            return paymentResponse;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new PaymentException("Payment failed, try again");
        }

    }
}
