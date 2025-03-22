package com.BK.Expense.repository;

import com.BK.Expense.entity.ExpenseRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRequestRepository extends JpaRepository<ExpenseRequest, Long> {

}
