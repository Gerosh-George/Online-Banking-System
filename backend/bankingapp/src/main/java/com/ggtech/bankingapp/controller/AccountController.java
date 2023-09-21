package com.ggtech.bankingapp.controller;

import com.ggtech.bankingapp.exceptions.NoDataFoundException;
import com.ggtech.bankingapp.exceptions.ResourceNotFoundException;
import com.ggtech.bankingapp.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.ggtech.bankingapp.model.Account;
import com.ggtech.bankingapp.service.AccountsService;

import java.util.List;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
public class AccountController {

    @Autowired
    AccountsService accountsService;



    @PostMapping("/account/{uid}")
    public String createAccount(@Valid @RequestBody Account account, @PathVariable("uid") Long userid) throws NoDataFoundException {
        String result = "";
        Account acc = accountsService.createAccount(account, userid);

        if(acc != null)
            result = "Account created!";
        else
            result = "Account creation failed!";
        return result;
    }

   @GetMapping("/account/{uid}")
    public List<Account> getAccount(@PathVariable("uid") Long userid)
    {
        return  accountsService.getUserAccounts(userid);

    }


    @GetMapping("/transactions/{accno}")
    public List<Transaction> fetchTransactions(@PathVariable("accno") long accno) throws NoDataFoundException
    {
        return accountsService.fetchTransactions(accno);
    }

    @GetMapping("/getAccountDetails/{accno}")
    public Account getAccountDetails(@PathVariable("accno") long accno) throws ResourceNotFoundException
    {
        return accountsService.getAccountDetails(accno);
    }

    @GetMapping("/checkBalance/{accno}")
    public double checkBalance(@PathVariable("accno") long accno)
    {
        return accountsService.checkBalance(accno);
    }

    @GetMapping("/allAccountBalance/{cid}")
    public double getAllAccountBalance(@PathVariable("cid") long cid) throws ResourceNotFoundException{
        return accountsService.getAllAccountBalance(cid);
    }
}