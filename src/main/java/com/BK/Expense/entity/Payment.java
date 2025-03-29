package com.BK.Expense.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "payments")
@Getter
@Setter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "expense_id", nullable = false, unique = true)
    private ExpenseRequest expenseRequest; // Links payment to an expense

    private double amount;

    private String paymentMethod; // e.g., Bank Transfer, PayPal, etc.

    private Instant createAt;

    private String createdBy;


}
