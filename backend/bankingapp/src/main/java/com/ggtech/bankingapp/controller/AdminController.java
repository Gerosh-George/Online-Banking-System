package com.ggtech.bankingapp.controller;

import com.ggtech.bankingapp.exceptions.BalanceInsufficientException;
import com.ggtech.bankingapp.model.Account;
import com.ggtech.bankingapp.model.AddBalanceRequest;
import com.ggtech.bankingapp.model.Admin;
import com.ggtech.bankingapp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/save")
    public String saveAdmin(@Valid @RequestBody Admin admin) {
        return adminService.saveAdmin(admin);
    }

    @PostMapping("/login")
    public String adminLogin(@Valid @RequestBody Admin admin) {
        return adminService.login(admin);
    }

    @PostMapping("/toggleAccountStatus/{accno}")
    public String toggleDisable(@PathVariable("accno") long accno) {
        return adminService.toggleAccountStatus(accno);
    }


    @GetMapping("/accounts")
    public List<Account> getAccounts() {
        return adminService.getAccounts();
    }

    @PostMapping("updateBalance")
    public String updateBalance(@Valid @RequestBody AddBalanceRequest req) throws BalanceInsufficientException {
        return adminService.updateCustomerBalance(req.getAccno(), req.getAmount());
    }

    @PostMapping("addFund")
    public String addFund(@Valid @RequestBody AddBalanceRequest req) throws BalanceInsufficientException {
        return adminService.addCustomerBalance(req.getAccno(),req.getAmount());
    }


}
