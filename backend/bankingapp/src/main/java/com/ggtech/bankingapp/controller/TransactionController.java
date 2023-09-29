package com.ggtech.bankingapp.controller;

import com.ggtech.bankingapp.model.AccountStatement;
import com.ggtech.bankingapp.model.Transaction;
import com.ggtech.bankingapp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin("*")
public class TransactionController {
    @Autowired
    TransactionService transService;

    @PostMapping("/transact")
    @PreAuthorize("hasAuthority('ROLE_CUSTOMER')")
    public String Transact(@RequestBody Transaction transaction) throws Exception {
        String result = "";
        Transaction trans = transService.transact(transaction);

        if (trans == null || Objects.equals(trans.getStatus(), "FAIL"))
            result = "Transaction Failed";
        else
            result = "Transaction Success";
        return result;
    }
    @GetMapping ("/accountStatement/{accno}")
    @PreAuthorize("hasAuthority('ROLE_CUSTOMER')")
    public List<Transaction> accountStatement(@PathVariable("accno") long accno, @RequestBody AccountStatement accountStatement) throws ParseException {
        return transService.accountStatement(accountStatement.getStartDate(), accountStatement.getEndDate(), accno);
    }

    @GetMapping("/accountSummary/{accno}")
    @PreAuthorize("hasAuthority('ROLE_CUSTOMER')")
    public List<Transaction> accountSummary(@PathVariable("accno") long accno)
    {
        return transService.accountSummary(accno);
    }

}
