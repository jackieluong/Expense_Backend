package com.BK.Expense.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RequestAcceptDto {
    private long requestId;

    private boolean financeAccept;
}
