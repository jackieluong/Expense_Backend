package com.BK.Expense.controller;

import com.BK.Expense.dto.*;

import com.BK.Expense.service.IRequestService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<ResultObject> createJob(@Valid @RequestBody CreateRequestDto createRequest) {

        ExpenseRequestDto expenseRequest = requestService.createExpenseRequest(createRequest);

        ResultObject resultObject = ResultObject.builder()
                .httpStatus(HttpStatus.CREATED)
                .message("Create request successsfully")
                .isSuccess(true)
                .data(expenseRequest)
                .build();
        return new ResponseEntity(resultObject, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ResultObject> updateRequest(@Valid @RequestBody UpdateRequestDto updateRequest, @PathVariable(name = "id") long id) {

        ExpenseRequestDto result = requestService.updateExpenseRequest(updateRequest, id);

        ResultObject resultObject = ResultObject.builder()
                .httpStatus(HttpStatus.CREATED)
                .message("Update successfully")
                .isSuccess(true)
                .data(result)
                .build();

        return new ResponseEntity(resultObject, HttpStatus.OK);

    }

    @PutMapping("/accept")
    public ResponseEntity<ResultObject> acceptRequest(@Valid @RequestBody RequestAcceptDto requestAccept) {

        ExpenseRequestDto result = requestService.acceptExpenseRequest(requestAccept);

        ResultObject resultObject = ResultObject.builder()
                .httpStatus(HttpStatus.OK)
                .message("Update successfully")
                .isSuccess(true)
                .data(result)
                .build();

        return new ResponseEntity(resultObject, HttpStatus.OK);

    }
    @PutMapping("/reject")
    public ResponseEntity<ResultObject> rejectRequest(@Valid @RequestBody RequestRejectDto requestReject) {

        ExpenseRequestDto result = requestService.rejectExpenseRequest(requestReject);

        ResultObject resultObject = ResultObject.builder()
                .httpStatus(HttpStatus.OK)
                .message("Update successfully")
                .isSuccess(true)
                .data(result)
                .build();

        return new ResponseEntity(resultObject, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<ResultPagination> getRequestsPagination(@RequestParam(value = "currentPage", defaultValue = "1") int current,
                                                              @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                                              @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
                                                              @RequestParam(value = "ascending", defaultValue = "true") String isAscending) {

        Page<GetRequestDto> requestPage = requestService.getAllRequest(current - 1, pageSize, sortBy, Boolean.valueOf(isAscending));


        ResultPagination<GetRequestDto> res = ResultPagination.<GetRequestDto>builder()
                .isSuccess(true)
                .message("Get requests successfully")
                .httpStatus(HttpStatus.OK)
                .currentPage(requestPage.getNumber() + 1)
                .pageSize(requestPage.getSize())
                .previousPage(requestPage.hasPrevious() ? requestPage.getNumber() : null)
                .nextPage(requestPage.hasNext() ? requestPage.getNumber() + 2 : null)
                .data(requestPage.getContent())
                .totalPage(requestPage.getTotalPages())
                .totalElement(requestPage.getTotalElements())
                .build();


        return new ResponseEntity<>(res, HttpStatus.OK);
    }


    @GetMapping("/employee")
    public ResponseEntity<ResultPagination> getRequestsByEmployeePagination(
                                                                  @RequestParam(value = "currentPage", defaultValue = "1") int current,
                                                                  @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                                                  @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
                                                                  @RequestParam(value = "ascending", defaultValue = "true") String isAscending) {

        Page<GetRequestDto> requestPage = requestService.getAllRequestByEmployee( current - 1, pageSize, sortBy, Boolean.valueOf(isAscending));


        ResultPagination<GetRequestDto> res = ResultPagination.<GetRequestDto>builder()
                .isSuccess(true)
                .message("Get requests successfully")
                .httpStatus(HttpStatus.OK)
                .currentPage(requestPage.getNumber() + 1)
                .pageSize(requestPage.getSize())
                .previousPage(requestPage.hasPrevious() ? requestPage.getNumber() : null)
                .nextPage(requestPage.hasNext() ? requestPage.getNumber() + 2 : null)
                .data(requestPage.getContent())
                .totalPage(requestPage.getTotalPages())
                .totalElement(requestPage.getTotalElements())
                .build();


        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResultObject> deleteRequest(@PathVariable(name = "id") long id) {

        String msg = requestService.closeRequest(id);

        ResultObject resultObject = ResultObject.builder()
                .httpStatus(HttpStatus.OK)
                .message(msg)
                .isSuccess(true)
                .data(null)
                .build();

        return new ResponseEntity(resultObject, HttpStatus.OK);

    }

}
