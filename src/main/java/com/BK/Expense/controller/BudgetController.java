package com.BK.Expense.controller;

import com.BK.Expense.dto.BudgetResponse;
import com.BK.Expense.dto.PaymentRequest;
import com.BK.Expense.dto.PaymentResponse;
import com.BK.Expense.dto.ResultObject;
import com.BK.Expense.exception.PaymentException;
import com.BK.Expense.service.IBudgetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/budgets")
public class BudgetController {

    private IBudgetService budgetService;

    public BudgetController(IBudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping
    public ResponseEntity<ResultObject> getBudgetByEmployee(){

        BudgetResponse budgetByEmployee = budgetService.getBudgetByEmployee();

        ResultObject resultObject = ResultObject.builder()
                .httpStatus(HttpStatus.OK)
                .message("Get budget successfully")
                .isSuccess(true)
                .data(budgetByEmployee)
                .build();

        return new ResponseEntity(resultObject, HttpStatus.OK);

    }
}
