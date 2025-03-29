package com.BK.Expense.entity;

import com.BK.Expense.enums.PaymentMethodEnum;
import com.BK.Expense.security.JwtUtil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "payments")
@Getter
@Setter
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "expense_id", nullable = false)
    private ExpenseRequest expenseRequest; // Links payment to an expense

    private double amount;

    @Enumerated(EnumType.STRING)
    private PaymentMethodEnum paymentMethod; // e.g., Bank Transfer, PayPal, etc.

    private Instant createdAt;

    private String createdBy;

    @PrePersist
    public void handleBeforeCreate() {


        this.setCreatedBy(JwtUtil.getCurrentUserLogin()
                .orElse(null));
        this.setCreatedAt(Instant.now());
    }

}
