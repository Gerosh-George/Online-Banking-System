package com.ggtech.bankingapp.controller;

import com.ggtech.bankingapp.model.Account;
import com.ggtech.bankingapp.model.AddBalanceRequest;
import com.ggtech.bankingapp.model.Admin;
import com.ggtech.bankingapp.model.LoginResponse;
import com.ggtech.bankingapp.service.AdminService;
import com.ggtech.bankingapp.service.AuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/save")
    public ResponseEntity<LoginResponse> saveAdmin(@Valid @RequestBody Admin admin) throws Exception {
        return ResponseEntity.ok(authenticationService.adminSave(admin));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> adminLogin(@Valid @RequestBody Admin admin) throws Exception{
        return ResponseEntity.ok(authenticationService.adminLogin(admin));
    }

    @PostMapping("/toggleAccountStatus/{accno}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String toggleDisable(@PathVariable("accno") long accno) {
        return adminService.toggleAccountStatus(accno);
    }


    @GetMapping("/accounts")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Account> getAccounts() {
        return adminService.getAccounts();
    }

    @PostMapping("updateBalance")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String updateBalance(@Valid @RequestBody AddBalanceRequest req) {
        return adminService.updateCustomerBalance(req.getAccno(), req.getAmount());
    }

    @PostMapping("addFund")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addFund(@Valid @RequestBody AddBalanceRequest req) {
        return adminService.addCustomerBalance(req.getAccno(),req.getAmount());
    }


}
