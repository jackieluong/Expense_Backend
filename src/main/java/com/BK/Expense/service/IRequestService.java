package com.BK.Expense.service;

import com.BK.Expense.dto.ExpenseRequestDto;
import com.BK.Expense.entity.ExpenseRequest;

public interface IRequestService {

    ExpenseRequestDto createExpenseRequest(ExpenseRequestDto expenseRequest);
}
