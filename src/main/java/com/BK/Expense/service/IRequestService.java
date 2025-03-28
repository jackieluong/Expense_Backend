package com.BK.Expense.service;

import com.BK.Expense.dto.*;
import com.BK.Expense.entity.ExpenseRequest;
import org.springframework.data.domain.Page;

public interface IRequestService {

    ExpenseRequestDto createExpenseRequest(CreateRequestDto createRequest);

    ExpenseRequestDto updateExpenseRequest(ExpenseRequestDto expenseRequestDto, long id);

    ExpenseRequestDto acceptExpenseRequest(RequestAcceptDto requestAccept);

    ExpenseRequest findById(long id);

    ExpenseRequestDto rejectExpenseRequest(RequestRejectDto requestReject);

    Page<GetRequestDto> getAllRequest(int currentPage, int pageSize, String sortBy, boolean isAscending);

    Page<GetRequestDto> getAllRequestByEmployee(Long employeeId, int currentPage, int pageSize, String sortBy, boolean isAscending);

}
