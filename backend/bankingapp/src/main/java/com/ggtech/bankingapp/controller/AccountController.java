package com.ggtech.bankingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ggtech.bankingapp.model.Account;
import com.ggtech.bankingapp.service.AccountsService;

@RestController
public class AccountController {

    @Autowired
    AccountsService accountsService;

    @PostMapping("/saveAccount")
    public void saveAccount(@RequestBody Account a) {
        accountsService.saveAccounts(a);
    }

}