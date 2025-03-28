package com.BK.Expense.dto;

import com.BK.Expense.enums.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class GetRequestDto {
    private long id;

    private String name;
    private String description;

    private double expense;

    private String expenseType;


    private StatusEnum statusEnum;

    private Instant createdAt;

    private Instant updatedAt;

    private String createdBy;

    private String updatedBy;

    private Instant timeout;
}
