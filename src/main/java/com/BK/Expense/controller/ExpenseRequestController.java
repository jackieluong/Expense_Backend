package com.BK.Expense.controller;

import com.BK.Expense.dto.ExpenseRequestDto;
import com.BK.Expense.dto.ResultObject;
import com.BK.Expense.entity.ExpenseRequest;
import com.BK.Expense.service.IRequestService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/requests")
public class ExpenseRequestController {

    private IRequestService requestService;

    public ExpenseRequestController(IRequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    public ResponseEntity<ResultObject> createJob(@Valid @RequestBody ExpenseRequestDto expenseRequestDto) {

        ExpenseRequestDto expenseRequest = requestService.createExpenseRequest(expenseRequestDto);

        ResultObject resultObject = ResultObject.builder()
                .httpStatus(HttpStatus.CREATED)
                .message("Create request successsfully")
                .isSuccess(true)
                .data(expenseRequest)
                .build();

        return new ResponseEntity(resultObject, HttpStatus.CREATED);

    }
}
