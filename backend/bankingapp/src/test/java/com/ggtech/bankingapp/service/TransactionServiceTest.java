package com.ggtech.bankingapp.service;

import com.ggtech.bankingapp.model.Account;
import com.ggtech.bankingapp.model.Customer;
import com.ggtech.bankingapp.model.Transaction;
import com.ggtech.bankingapp.repository.AccountRepository;
import com.ggtech.bankingapp.repository.TransactionRepository;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {TransactionService.class})
@ExtendWith(SpringExtension.class)
class TransactionServiceTest {

    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionService transactionService;

    @Test
    void testTransact() throws Exception {
        Customer customer = new Customer();
        customer.setAadhar("123456789");
        customer.setAccount(new ArrayList<>());
        customer.setAddress("42 Main St");
        customer.setCustomerId(1L);
        customer.setDob("2001-09-03");
        customer.setEmail("jane.doe@example.org");
        customer.setFathername("Bob");
        customer.setMobile(987654321);
        customer.setMothername("Mary");
        customer.setName("Mary");
        customer.setPassword("12345");

        Account acc_no = new Account();
        acc_no.setAccountNo(1234567890L);
        acc_no.setAccountType("SAVINGS");
        acc_no.setBalance(10.0d);
        acc_no.setBranch("Embassy");
        acc_no.setCustomer(customer);
        acc_no.setDisabled(true);
        acc_no.setIfsc("12345");
        acc_no.setOpeneingDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        acc_no.setTransaction(new ArrayList<>());

        Transaction trans = new Transaction();
        trans.setAccFrom(1L);
        trans.setAccTo(1L);
        trans.setAcc_no(acc_no);
        trans.setAmount(10.0d);
        trans.setStatus("true");
        trans.setTimestamp(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        trans.setTransType("Trans Type");
        trans.setTransactionId(1L);
        assertThrows(Exception.class, () -> transactionService.transact(trans));
    }



    @Test
    void testAccountStatement() throws ParseException {
        when(transactionRepository.findByAccountNumber(anyLong())).thenReturn(new ArrayList<>());
        Date startDate = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        assertTrue(
                transactionService
                        .accountStatement(startDate,
                                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()), 3L)
                        .isEmpty());
        verify(transactionRepository).findByAccountNumber(anyLong());
    }



    @Test
    void testAccountSummary() {
        when(transactionRepository.findByAccountNumber(anyLong())).thenReturn(new ArrayList<>());
        assertTrue(transactionService.accountSummary(3L).isEmpty());
        verify(transactionRepository).findByAccountNumber(anyLong());
    }

}