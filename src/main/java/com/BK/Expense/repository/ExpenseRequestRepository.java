package com.BK.Expense.repository;

import com.BK.Expense.entity.ExpenseRequest;
import com.BK.Expense.enums.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRequestRepository extends JpaRepository<ExpenseRequest, Long> {

    Page<ExpenseRequest> findByEmployeeId(Long employeeId , Pageable pageable);


    Page<ExpenseRequest> findByStatusEnum(StatusEnum expenseStatus , Pageable pageable);
}
