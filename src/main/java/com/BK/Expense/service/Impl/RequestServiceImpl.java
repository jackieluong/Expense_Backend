package com.BK.Expense.service.Impl;

import com.BK.Expense.dto.*;
import com.BK.Expense.entity.Account;
import com.BK.Expense.entity.ExpenseRequest;
import com.BK.Expense.enums.RoleEnum;
import com.BK.Expense.enums.StatusEnum;
import com.BK.Expense.exception.ResourceNotFoundException;
import com.BK.Expense.repository.ExpenseRequestRepository;
import com.BK.Expense.security.JwtUtil;
import com.BK.Expense.service.IAccountService;
import com.BK.Expense.service.IRequestService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements IRequestService {

    private ExpenseRequestRepository expenseRequestRepository;
    private IAccountService accountService;
    private ModelMapper modelMapper;
    private JwtUtil jwtUtil;

    public RequestServiceImpl(ExpenseRequestRepository expenseRequestRepository, IAccountService accountService, ModelMapper modelMapper, JwtUtil jwtUtil) {
        this.expenseRequestRepository = expenseRequestRepository;
        this.accountService = accountService;
        this.modelMapper = modelMapper;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public ExpenseRequestDto createExpenseRequest(CreateRequestDto createRequest) {
//        Account employee = accountService.getAccountByEmail(expenseRequest.getEmployeeEmail());

        String email = JwtUtil.getCurrentUserLogin().orElseThrow(() -> new AuthorizationDeniedException("User is not logged in"));
        Account employee = accountService.getAccountByEmail(email);


        ExpenseRequest newRequest = ExpenseRequest.builder()
                .expense(createRequest.getExpense())
                .name(createRequest.getName())
                .description(createRequest.getDescription())
                .statusEnum(StatusEnum.PENDING)
                .expenseType(createRequest.getExpenseType())
                .employee(employee)
                .build();

        ExpenseRequest savedRequest = expenseRequestRepository.save(newRequest);

        return modelMapper.map(savedRequest, ExpenseRequestDto.class);
    }

    @Override
    public ExpenseRequestDto updateExpenseRequest(UpdateRequestDto updateRequest, long id) {
        ExpenseRequest expenseRequest = expenseRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Request", "id", id));

//        expenseRequest.setStatusEnum(expenseRequestDto.getStatusEnum());
        expenseRequest.setName(updateRequest.getName());
        expenseRequest.setDescription(updateRequest.getDescription());
        expenseRequest.setExpenseType(updateRequest.getExpenseType());
        expenseRequest.setExpense(updateRequest.getExpense());

        ExpenseRequest updatedRequest = expenseRequestRepository.save(expenseRequest);

        return modelMapper.map(updatedRequest, ExpenseRequestDto.class);
    }

    @Override
    public ExpenseRequestDto acceptExpenseRequest(RequestAcceptDto requestAccept) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || !(authentication.getPrincipal() instanceof Jwt jwt)){
            throw new RuntimeException("User is not authenticated");
        }

        String token = jwt.getTokenValue();
        String userRole = jwtUtil.extractUserRoleFromToken(token);

        if(userRole.equals(RoleEnum.EMPLOYEE.toString()) || (requestAccept.isFinanceAccept() && !userRole.equals( RoleEnum.FINANCE_MANAGER.toString()))){
            throw new AccessDeniedException("You do not have permission to modify this resource");
        }

        ExpenseRequest expenseRequest = findById(requestAccept.getRequestId());

        expenseRequest.setStatusEnum(requestAccept.isFinanceAccept()
                ? StatusEnum.FINANCE_ACCEPTED
                : StatusEnum.MANAGER_ACCEPTED
        );

        ExpenseRequest updatedRequest = expenseRequestRepository.save(expenseRequest);

        return modelMapper.map(updatedRequest, ExpenseRequestDto.class);

    }

    @Override
    public ExpenseRequest findById(long id) {

        ExpenseRequest expenseRequest =  expenseRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Request", "id", id));

        return expenseRequest;
    }

    @Override
    public ExpenseRequestDto rejectExpenseRequest(RequestRejectDto requestReject) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || !(authentication.getPrincipal() instanceof Jwt jwt)){
            throw new RuntimeException("User is not authenticated");
        }

        String token = jwt.getTokenValue();
        String userRole = jwtUtil.extractUserRoleFromToken(token);


        if(userRole.equals(RoleEnum.EMPLOYEE.toString()) ){
            throw new AccessDeniedException("You do not have permission to modify this resource");
        }

        ExpenseRequest expenseRequest = findById(requestReject.getRequestId());

        expenseRequest.setStatusEnum(StatusEnum.REJECTED
        );

        ExpenseRequest updatedRequest = expenseRequestRepository.save(expenseRequest);

        return modelMapper.map(updatedRequest, ExpenseRequestDto.class);
    }

    @Override
    public Page<GetRequestDto> getAllRequest(int currentPage, int pageSize, String sortBy, boolean isAscending) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || !(authentication.getPrincipal() instanceof Jwt jwt)){
            throw new RuntimeException("User is not authenticated");
        }

        String token = jwt.getTokenValue();
        String userRole = jwtUtil.extractUserRoleFromToken(token);


        Sort sort = isAscending ? Sort.by(sortBy) : Sort.by(sortBy).descending();

        PageRequest pageRequest = PageRequest.of(currentPage, pageSize, sort);

        Page<ExpenseRequest> expenseRequestPage;

        if(userRole.equals(RoleEnum.FINANCE_MANAGER.toString())){
            expenseRequestPage =  expenseRequestRepository.findByStatusEnum(StatusEnum.MANAGER_ACCEPTED, pageRequest);
        }else{
            expenseRequestPage =  expenseRequestRepository.findAll(pageRequest);
        }



        Page<GetRequestDto> requestResPage = expenseRequestPage.map(
                expenseRequest -> modelMapper.map(expenseRequest, GetRequestDto.class));
//        ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)


        return requestResPage;
    }

    @Override
    public Page<GetRequestDto> getAllRequestByEmployee(int currentPage, int pageSize, String sortBy, boolean isAscending) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || !(authentication.getPrincipal() instanceof Jwt jwt)){
            throw new RuntimeException("User is not authenticated");
        }

        String token = jwt.getTokenValue();
        String userId = jwtUtil.extractUserIdFromToken(token);

        Sort sort = isAscending ? Sort.by(sortBy) : Sort.by(sortBy).descending();

        PageRequest pageRequest = PageRequest.of(currentPage, pageSize, sort);


        Page<ExpenseRequest> expenseRequestPage= expenseRequestRepository.findByEmployeeId(Long.valueOf(userId),pageRequest);

        Page<GetRequestDto> requestResPage = expenseRequestPage.map(
                expenseRequest -> modelMapper.map(expenseRequest, GetRequestDto.class));
//        ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)


        return requestResPage;
    }

    @Override
    public String closeRequest(long id) {
        ExpenseRequest expense = findById(id);


        expenseRequestRepository.delete(expense);

        return "Delete successfully";
    }


}
