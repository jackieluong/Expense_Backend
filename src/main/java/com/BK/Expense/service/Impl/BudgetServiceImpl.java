package com.BK.Expense.service.Impl;

import com.BK.Expense.dto.BudgetResponse;
import com.BK.Expense.entity.Budget;
import com.BK.Expense.repository.BudgetRepository;
import com.BK.Expense.security.JwtUtil;
import com.BK.Expense.service.IBudgetService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class BudgetServiceImpl implements IBudgetService {

    private BudgetRepository budgetRepository;
    private ModelMapper modelMapper;
    private JwtUtil jwtUtil;

    public BudgetServiceImpl(BudgetRepository budgetRepository, ModelMapper modelMapper, JwtUtil jwtUtil) {
        this.budgetRepository = budgetRepository;
        this.modelMapper = modelMapper;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public BudgetResponse getBudgetByEmployee() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || !(authentication.getPrincipal() instanceof Jwt jwt)){
            throw new RuntimeException("User is not authenticated");
        }

        String token = jwt.getTokenValue();
        String userId = jwtUtil.extractUserIdFromToken(token);

        Budget budget = budgetRepository.getBudgetByAccountId(Long.parseLong(userId));

        return modelMapper.map(budget, BudgetResponse.class);
    }
}
