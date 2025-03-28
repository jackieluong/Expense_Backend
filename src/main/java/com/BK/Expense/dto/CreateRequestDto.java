package com.BK.Expense.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CreateRequestDto {
    private String name;

    private String description;

    @NotNull(message = "Expense must not be null")
    @Min(value = 0, message = "Expense must be greater than 0")
    private double expense;

    private String employeeEmail;

    @NotBlank(message = "Expense Type must not be blank")
    private String expenseType;

}
