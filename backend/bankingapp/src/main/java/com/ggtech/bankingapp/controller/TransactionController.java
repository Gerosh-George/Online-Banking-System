package com.ggtech.bankingapp.controller;

import com.ggtech.bankingapp.model.Transaction;
import com.ggtech.bankingapp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
    public String Transact(@RequestBody Transaction transaction) {
        String result = "";
        Transaction trans = transService.transact(transaction);

        if (trans == null || Objects.equals(trans.getStatus(), "FAIL"))
            result = "Transaction Failed";
        else
            result = "Transaction Success";
        return result;
    }
    @GetMapping ("/accountStatement/{accno}/{startDate}/{endDate}")
    public List<Transaction> accountStatement(@PathVariable("accno") long accno, @PathVariable ("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate, @PathVariable("endDate")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) throws ParseException {
        return transService.accountStatement(startDate, endDate, accno);
    }

    @GetMapping("/accountSummary/{accno}")
    public List<Transaction> accountSummary(@PathVariable("accno") long accno)
    {
        return transService.accountSummary(accno);
    }

}
