package com.BK.Expense.controller;

import com.BK.Expense.dto.ExpenseRequestDto;
import com.BK.Expense.dto.ResultObject;
import com.BK.Expense.entity.ExpenseRequest;
import com.BK.Expense.service.IRequestService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping
    public ResponseEntity<ResultObject> updateRequest(@Valid @RequestBody ExpenseRequestDto expenseRequestDto, @RequestParam(name = "id") long id) {

        ExpenseRequestDto result = requestService.updateExpenseRequest(expenseRequestDto, id);

        ResultObject resultObject = ResultObject.builder()
                .httpStatus(HttpStatus.CREATED)
                .message("Update successfully")
                .isSuccess(true)
                .data(result)
                .build();

        return new ResponseEntity(resultObject, HttpStatus.OK);

    }


}
