package com.ggtech.bankingapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ggtech.bankingapp.exceptions.NoDataFoundException;
import com.ggtech.bankingapp.exceptions.ResourceNotFoundException;
import com.ggtech.bankingapp.model.Account;
import com.ggtech.bankingapp.model.Customer;
import com.ggtech.bankingapp.model.Transaction;
import com.ggtech.bankingapp.repository.AccountRepository;
import com.ggtech.bankingapp.repository.CustomerRepository;
import com.ggtech.bankingapp.repository.TransactionRepository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AccountsService.class})
@ExtendWith(SpringExtension.class)
class AccountsServiceTest {
    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private AccountsService accountsService;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private TransactionRepository transactionRepository;





    @Test
    void testFetchTransactions() throws NoDataFoundException {
        when(transactionRepository.findByAccountNumber(anyLong())).thenReturn(new ArrayList<>());
        assertThrows(NoDataFoundException.class, () -> accountsService.fetchTransactions(1L));
        verify(transactionRepository).findByAccountNumber(anyLong());
    }

    @Test
    void testGetAccountDetails() throws ResourceNotFoundException {
        Customer customer = new Customer();
        customer.setAadhar("123456789012");
        customer.setAccount(new ArrayList<>());
        customer.setAddress("42 Main St");
        customer.setCustomerId(1L);
        customer.setDob("03-09-2001");
        customer.setEmail("jane.doe@example.org");
        customer.setFathername("Bob");
        customer.setMobile(1L);
        customer.setMothername("Mary");
        customer.setName("Jane");
        customer.setPassword("password");

        Account account = new Account();
        account.setAccountNo(1234567890L);
        account.setAccountType("3");
        account.setBalance(10.0d);
        account.setBranch("Embassy");
        account.setCustomer(customer);
        account.setDisabled(true);
        account.setIfsc("12345");
        account.setOpeneingDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        Optional<Account> ofResult = Optional.of(account);
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertSame(account, accountsService.getAccountDetails(3L));
        verify(accountRepository).findById(Mockito.<Long>any());
    }



}

