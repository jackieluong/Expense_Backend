package com.BK.Expense.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestRejectDto {
    @NotNull(message = "Request id must not be null")
    private long requestId;
}
