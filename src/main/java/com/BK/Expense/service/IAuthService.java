package com.BK.Expense.service;

import com.BK.Expense.dto.AccountDto;
import com.BK.Expense.dto.LoginDto;
import com.BK.Expense.dto.RegisterDto;
import com.BK.Expense.dto.ResultObject;

import com.BK.Expense.entity.Account;

public interface IAuthService {
    String login(LoginDto loginDto, Account user);

    AccountDto registerUser(RegisterDto registerDto);
}
