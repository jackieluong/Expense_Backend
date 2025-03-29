package com.BK.Expense.dto;

import com.BK.Expense.entity.ExpenseRequest;
import com.BK.Expense.enums.PaymentMethodEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jdk.jfr.StackTrace;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class PaymentResponse {
    private Long id;

    private boolean isSuccess;

    private String requestName; // Links payment to an expense

    private double amount;

    private PaymentMethodEnum paymentMethod; // e.g., Bank Transfer, PayPal, etc.

    private Instant createdAt;

    private String createdBy;

}
