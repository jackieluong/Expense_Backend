package com.BK.Expense.dto;


import com.BK.Expense.enums.StatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ExpenseRequestDto {

    private String description;

    private double expense;

    private String employeeEmail;

    private Instant startDate;

    private Instant endDate;

    private StatusEnum statusEnum;
}
