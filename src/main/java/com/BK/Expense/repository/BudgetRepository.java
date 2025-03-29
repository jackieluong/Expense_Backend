package com.BK.Expense.repository;

import com.BK.Expense.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;

public interface BudgetRepository extends JpaRepository<Budget, Long> {

    Budget getBudgetByAccountId(long employeeId);

    @Modifying
    @Query("""
            UPDATE Budget b
            SET b.amount = b.amount + :amount
            WHERE b.account.id = :employeeId"""
    )
    void updateBudgetAmount(@Param("employeeId") Long employeeId, @Param("amount") double amount );
}
