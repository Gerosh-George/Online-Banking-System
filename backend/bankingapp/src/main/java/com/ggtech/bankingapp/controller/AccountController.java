package com.ggtech.bankingapp.controller;

import com.ggtech.bankingapp.exceptions.NoDataFoundException;
import com.ggtech.bankingapp.exceptions.ResourceNotFoundException;
import com.ggtech.bankingapp.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ggtech.bankingapp.model.Account;
import com.ggtech.bankingapp.service.AccountsService;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    AccountsService accountsService;


    @PostMapping("/createAccount/{uid}")
    public String createAccount(@RequestBody Account account, @PathVariable("uid") Long userid)
    {
        String result = "";
        Account acc = accountsService.createAccount(account, userid);

        if(acc != null)
            result = "Account created!";
        else
            result = "Account creation failed!";
        return result;
    }

    @GetMapping("/fetchTransactions/{accno}")
    public List<Transaction> fetchTransactions(@PathVariable("accno") long accno) throws NoDataFoundException
    {
        List<Transaction> result = accountsService.fetchTransactions(accno);
        return result;
    }

    @GetMapping("/getAccountDetails/{accno}")
    public Account getAccountDetails(@PathVariable("accno") long accno) throws ResourceNotFoundException
    {
        return accountsService.getAccountDetails(accno);
    }
}