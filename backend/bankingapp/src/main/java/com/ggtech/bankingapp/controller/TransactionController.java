package com.ggtech.bankingapp.controller;

import com.ggtech.bankingapp.model.Transaction;
import com.ggtech.bankingapp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@CrossOrigin("*")
public class TransactionController {
    @Autowired
    TransactionService transService;

    @PostMapping("/transact")
    public String Transact(@RequestBody Transaction transaction) {
        String result = "";
        Transaction trans = transService.transact(transaction);

        if (trans == null || Objects.equals(trans.getStatus(), "FAIL"))
            result = "Transaction Failed";
        else
            result = "Transaction Success";
        return result;
    }
}
