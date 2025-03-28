package com.BK.Expense.service;

import com.BK.Expense.dto.BudgetResponse;

public interface IBudgetService {
    BudgetResponse getBudgetByEmployee();
    String updateBudgetAmount(Long employeeId, double amount);
}
