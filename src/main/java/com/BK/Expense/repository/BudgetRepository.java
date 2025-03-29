package com.BK.Expense.repository;

import com.BK.Expense.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Long> {

    Budget getBudgetByAccountId(long employeeId);
}
