package com.BK.Expense.service;

import com.BK.Expense.entity.Account;


public interface IAccountService {
    Account getAccountByEmail(String email);
}
