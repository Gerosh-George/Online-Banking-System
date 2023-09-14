package com.ggtech.bankingapp.service;

import com.ggtech.bankingapp.model.Account;
import com.ggtech.bankingapp.model.Admin;
import com.ggtech.bankingapp.repository.AccountRepository;
import com.ggtech.bankingapp.repository.AdminRepository;
import com.ggtech.bankingapp.repository.CustomerRepository;
import com.ggtech.bankingapp.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    CustomerRepository custRepo;

    @Autowired
    AccountRepository accRepo;

    @Autowired
    TransactionRepository transRepo;

    @Autowired
    AdminRepository adminRepo;

    public String saveAdmin(Admin admin)
    {
        String result = "";
        Optional<Admin> o = adminRepo.findById(admin.getUserid());
        if(o.isEmpty()) {
            Admin obj = adminRepo.save(admin);
            return "Admin created successfully!";

        }
        return "Admin already exists!";
    }

    public String login(Admin u)
    {
        Admin admin = null;
        String result = "";

        Optional<Admin> obj = adminRepo.findById(u.getUserid());

        if(obj.isPresent())
        {
            admin = obj.get();
        }
        if(admin == null)
        {
            return "Invalid Admin details";
        }
        else
        {
            if(!u.getPassword().equals(admin.getPassword()))
                return "Login failed";
        }
        return "Login success";
    }

    public String toggleAccountStatus(long accno)
    {
        if(accRepo.findById(accno).isEmpty())
            return "Account not found";
        Account acc = accRepo.findById(accno).get();
        if(acc.isDisabled())
        {
            acc.setDisabled(false);
            accRepo.save(acc);
            return "Account enabled";
        }
        else {

                acc.setDisabled(true);
                accRepo.save(acc);
                return "Account Disabled";
        }
    }

    public List<Account> getAccounts() {
        return accRepo.findAll();
    }

}
