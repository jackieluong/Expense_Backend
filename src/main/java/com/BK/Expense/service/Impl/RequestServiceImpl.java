package com.BK.Expense.service.Impl;

import com.BK.Expense.dto.ExpenseRequestDto;
import com.BK.Expense.entity.Account;
import com.BK.Expense.entity.ExpenseRequest;
import com.BK.Expense.enums.StatusEnum;
import com.BK.Expense.repository.ExpenseRequestRepository;
import com.BK.Expense.service.IAccountService;
import com.BK.Expense.service.IRequestService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements IRequestService {

    private ExpenseRequestRepository expenseRequestRepository;
    private IAccountService accountService;
    private ModelMapper modelMapper;

    public RequestServiceImpl(ExpenseRequestRepository expenseRequestRepository, IAccountService accountService, ModelMapper modelMapper) {
        this.expenseRequestRepository = expenseRequestRepository;
        this.accountService = accountService;
        this.modelMapper = modelMapper;
    }

    @Override
    public ExpenseRequestDto createExpenseRequest(ExpenseRequestDto expenseRequest) {
        Account employee = accountService.getAccountByEmail(expenseRequest.getEmployeeEmail());

        ExpenseRequest newRequest = ExpenseRequest.builder()
                .expense(expenseRequest.getExpense())
                .description(expenseRequest.getDescription())
                .statusEnum(StatusEnum.PENDING)
                .expenseType(expenseRequest.getExpenseType())
                .employee(employee)
                .build();

        ExpenseRequest savedRequest = expenseRequestRepository.save(newRequest);

        return modelMapper.map(savedRequest, ExpenseRequestDto.class);
    }
}
