package com.ggtech.bankingapp.service;

import com.ggtech.bankingapp.exceptions.BalanceInsufficientException;
import com.ggtech.bankingapp.exceptions.NoDataFoundException;
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

    public String login(Admin u) throws NoDataFoundException {
        Admin admin = null;
        String result = "";

        Optional<Admin> obj = adminRepo.findById(u.getUserid());

        if(obj.isPresent())
        {
            admin = obj.get();
        }
        if(admin == null)
        {
            throw new NoDataFoundException("admin with this ID does not exist");
        }
        else
        {
            if(!u.getPassword().equals(admin.getPassword()))
                return "Login failed, please check the password";
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

    public String updateCustomerBalance(long accno, double balance) throws BalanceInsufficientException {
        Account acc = accRepo.findById(accno).orElse(null);
        if(acc==null){
            return "Account doesn't exist";
        }
        if(balance<=0){
            throw new BalanceInsufficientException("Amount can't be <= 0");
        }
        acc.setBalance(balance);
        accRepo.save(acc);
        return "Balance updated successfully";

    }

    public String addCustomerBalance(long accno, double balance) throws BalanceInsufficientException {
        Account acc = accRepo.findById(accno).orElse(null);
        if(acc==null){
            return "Account doesn't exist";
        }
        double b = acc.getBalance();
        if(balance<=0){
            throw new BalanceInsufficientException("Amount can't be <= 0");
        }
        acc.setBalance(b+balance);
        accRepo.save(acc);
        return "Fund added to the account balance successfully";

    }

}
