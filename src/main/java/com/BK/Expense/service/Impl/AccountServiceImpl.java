package com.BK.Expense.service.Impl;

import com.BK.Expense.entity.Account;
import com.BK.Expense.exception.ResourceNotFoundException;
import com.BK.Expense.repository.AccountRepository;
import com.BK.Expense.service.IAccountService;

import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements IAccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account getAccountByEmail(String email) {
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "email", email));


        return account;
    }
}
