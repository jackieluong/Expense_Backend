package com.BK.Expense.dto;


import com.BK.Expense.enums.StatusEnum;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseRequestDto {

    private String description;

    private double expense;

    private String employeeEmail;

    private String expenseType;

//    private Instant startDate;
//
//    private Instant endDate;

    private StatusEnum statusEnum;

    private Instant createdAt;

    private Instant updatedAt;

    private String createdBy;

    private String updatedBy;

    private Instant timeout;

}
