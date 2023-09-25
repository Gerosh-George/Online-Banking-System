package com.ggtech.bankingapp.service;

import com.ggtech.bankingapp.exceptions.NoDataFoundException;
import com.ggtech.bankingapp.exceptions.ResourceNotFoundException;
import com.ggtech.bankingapp.model.Customer;
import com.ggtech.bankingapp.model.Transaction;
import com.ggtech.bankingapp.repository.CustomerRepository;
import com.ggtech.bankingapp.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ggtech.bankingapp.model.Account;
import com.ggtech.bankingapp.repository.AccountRepository;

import java.util.List;
import java.util.Random;

@Service
public class AccountsService {

    @Autowired
    AccountRepository accountsRepo;
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    TransactionRepository transactionRepository;


    public Account createAccount(Account account, Long userid) throws NoDataFoundException {
        long generatedNumber = 0;
        Random rand = new Random();
        generatedNumber = Math.abs(999999 + rand.nextLong());
        generatedNumber = generatedNumber % 100000000000L;
        while(accountsRepo.findById(generatedNumber).isPresent())
        {
            generatedNumber = Math.abs(999999 + rand.nextLong());
            generatedNumber = generatedNumber % 100000000000L;
        }
        Customer u = customerRepository.findById(userid).orElse(null);
        if(u==null){
            throw new NoDataFoundException("Customer with this id doesn't exist");
        }
        String branch = account.getBranch();
        String ifsc = branch.substring(0, 3) + (int)(branch.charAt(branch.length()-1)) + (int)(branch.charAt(branch.length()-2));
        account.setCustomer(u);
        account.setAccountNo(generatedNumber);
        account.setIfsc(ifsc);
        account.setDisabled(true);
        return accountsRepo.save(account);
    }


    public List<Transaction> fetchTransactions(long accno) throws NoDataFoundException
    {
        if(transactionRepository.findByAccountNumber(accno).isEmpty())
        {
            throw new NoDataFoundException("No Transactions to Display");
        }
        return transactionRepository.findByAccountNumber(accno);
    }

    public Account getAccountDetails(long accno) throws ResourceNotFoundException
    {
        Account acc = accountsRepo.findById(accno).orElse(null);
        if(acc==null)
            throw new ResourceNotFoundException("Account Not Found");
        else
            return acc;
    }

    public double checkBalance(long accno)
    {
        return accountsRepo.findById(accno).get().getBalance();
    }

    public List<Account> getUserAccounts(long uid) throws ResourceNotFoundException{
        Customer cust = customerRepository.findById(uid).orElse(null);
        assert cust != null;
        if(cust.getAccount().isEmpty()) {
        	throw new ResourceNotFoundException("CUSTOMER DOES NOT HAVE ANY ACCOUNTS");
        }
        return cust.getAccount();
    }

    public Double getAllAccountBalance(long cid) throws ResourceNotFoundException {
        Customer cust = customerRepository.findById(cid).orElse(null);
        if(cust==null){
            throw new ResourceNotFoundException("Customer with this id does not exist!");
        }
        List<Account> accountList = cust.getAccount();
        double total=0;
        for(Account acc: accountList){
            total+=acc.getBalance();
        }
        return total;
    }

}
