package com.BK.Expense.entity;

import com.BK.Expense.security.JwtUtil;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "budgets")
@Getter
@Setter
@NoArgsConstructor
public class Budget {
    @Id
    private Long employeeId;

    private double amount;

    @OneToOne
    @MapsId
    @JoinColumn(name = "employee_id", unique = true, nullable = false)
    private Account account;

    private Instant createdAt;

    private Instant updatedAt;

    private String createdBy;

    private String updatedBy;

    // Before add a new job, set CreatedBy to email who add the job
    @PrePersist
    public void handleBeforeCreate() {



        this.setCreatedBy(JwtUtil.getCurrentUserLogin()
                .orElse(null));
        this.setCreatedAt(Instant.now());
    }

    @PreUpdate
    public void handleBeforeUpdate() {

        this.setUpdatedBy(JwtUtil.getCurrentUserLogin()
                .orElse(null));
        this.setUpdatedAt(Instant.now());
    }

}
