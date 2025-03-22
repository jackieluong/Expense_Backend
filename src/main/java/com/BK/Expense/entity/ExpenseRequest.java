package com.BK.Expense.entity;

import com.BK.Expense.enums.StatusEnum;
import com.BK.Expense.security.JwtUtil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ExpenseRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    private String description;

    @Enumerated(EnumType.STRING)
    private StatusEnum statusEnum;

    private double expense;

    private Instant timeout;

    private String expenseType;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Account employee;

//    @ManyToOne
//    @JoinColumn(name = "trip_id")
//    private Trip trip;

    private Instant createdAt;

    private Instant updatedAt;

    private String createdBy;

    private String updatedBy;

    // Before add a new job, set CreatedBy to email who add the job
    @PrePersist
    public void handleBeforeCreate() {

        this.setTimeout(Instant.now().plusSeconds(7*24*60*60) ); // time out 7 days

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
