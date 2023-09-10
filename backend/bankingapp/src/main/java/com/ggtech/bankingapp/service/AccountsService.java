package com.ggtech.bankingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ggtech.bankingapp.model.Account;
import com.ggtech.bankingapp.repository.AccountRepository;

@Service
public class AccountsService {

    @Autowired
    AccountRepository accountsRepo;

    public Account saveAccounts(Account a)
    {
        return accountsRepo.save(a);
    }

}
