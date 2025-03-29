package com.BK.Expense.service.Impl;

import com.BK.Expense.dto.AccountDto;
import com.BK.Expense.dto.LoginDto;
import com.BK.Expense.dto.RegisterDto;
import com.BK.Expense.dto.ResultObject;

import com.BK.Expense.entity.Account;
import com.BK.Expense.entity.Budget;
import com.BK.Expense.enums.RoleEnum;
import com.BK.Expense.repository.AccountRepository;
import com.BK.Expense.security.JwtUtil;
import com.BK.Expense.service.IAuthService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthService {

    private AuthenticationManager authenticationManager;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private JwtUtil jwtUtil;
    private AccountRepository accountRepository;

    public AuthServiceImpl(AuthenticationManager authenticationManager, ModelMapper modelMapper, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AccountRepository accountRepository) {
        this.authenticationManager = authenticationManager;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.accountRepository = accountRepository;
    }

    @Override
    public String login(LoginDto loginDto, Account user) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = jwtUtil.createAccessToken(user);

        return jwtToken;
    }

    @Override
    @Transactional
    public AccountDto registerUser(RegisterDto registerDto) {
        if(accountRepository.existsByEmail(registerDto.getEmail())){
            throw new RuntimeException("User already exist");
        }

        Account account = new Account();
        account.setEmail(registerDto.getEmail());
        account.setName(registerDto.getName());
        account.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        account.setRole(registerDto.getRole());

        if(registerDto.getRole().equals(RoleEnum.EMPLOYEE)){
            Budget budget = new Budget();
            budget.setAmount(0);
            budget.setAccount(account);
            account.setBudget(budget);
        }
        Account savedAccount = accountRepository.save(account);

        return modelMapper.map(savedAccount, AccountDto.class);

    }
}
