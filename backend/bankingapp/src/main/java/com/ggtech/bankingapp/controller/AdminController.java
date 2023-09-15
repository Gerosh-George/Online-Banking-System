package com.ggtech.bankingapp.controller;

import com.ggtech.bankingapp.model.Account;
import com.ggtech.bankingapp.model.Admin;
import com.ggtech.bankingapp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/save")
    public String saveAdmin(@RequestBody Admin admin) {
        return adminService.saveAdmin(admin);
    }

    @PostMapping("/login")
    public String adminLogin(@RequestBody Admin admin) {
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

    @PostMapping("updateBalance/{accno}/{bal}")
    public String updateBalance(@PathVariable("accno") long accno, @PathVariable("bal") double bal) {
        return adminService.updateCustomerBalance(accno, bal);
    }

    @PostMapping("addFund/{accno}/{bal}")
    public String addFund(@PathVariable("accno") long accno, @PathVariable("bal") double bal) {
        return adminService.addCustomerBalance(accno, bal);
    }


}
