package com.BK.Expense.dto;


import com.BK.Expense.enums.StatusEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseRequestDto {

    private long id;

    private String name;

    private String description;

    @NotNull(message = "Expense must not be null")
    @Min(value = 0, message = "Expense must be greater than 0")
    private double expense;

    private String employeeEmail;

    @NotBlank(message = "Expense Type must not be blank")
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
