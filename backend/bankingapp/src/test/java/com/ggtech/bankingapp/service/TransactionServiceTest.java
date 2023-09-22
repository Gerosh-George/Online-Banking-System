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

    /**
     * Method under test: {@link TransactionService#transact(Transaction)}
     */
    @Test
    void testTransact() throws Exception {
        Customer customer = new Customer();
        customer.setAadhar("123456789");
        customer.setAccount(new ArrayList<>());
        customer.setAddress("42 Main St");
        customer.setCustomerId(1L);
        customer.setDob("2001-09-03");
        customer.setEmail("jane.doe@example.org");
        customer.setFathername("Fathername");
        customer.setMobile(1L);
        customer.setMothername("Mothername");
        customer.setName("Name");
        customer.setPassword("12345");

        Account acc_no = new Account();
        acc_no.setAccountNo(1234567890L);
        acc_no.setAccountType("3");
        acc_no.setBalance(10.0d);
        acc_no.setBranch("Embassy");
        acc_no.setCustomer(customer);
        acc_no.setDisabled(true);
        acc_no.setIfsc("Ifsc");
        acc_no.setOpeneingDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        acc_no.setTransaction(new ArrayList<>());

        Transaction trans = new Transaction();
        trans.setAccFrom(1L);
        trans.setAccTo(1L);
        trans.setAcc_no(acc_no);
        trans.setAmount(10.0d);
        trans.setStatus("Status");
        trans.setTimestamp(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        trans.setTransType("Trans Type");
        trans.setTransactionId(1L);
        assertThrows(Exception.class, () -> transactionService.transact(trans));
    }

    /**
     * Method under test: {@link TransactionService#transact(Transaction)}
     */
    @Test
    void testTransact2() throws Exception {
        Customer customer = new Customer();
        customer.setAadhar("123456789");
        customer.setAccount(new ArrayList<>());
        customer.setAddress("42 Main St");
        customer.setCustomerId(1L);
        customer.setDob("2001-09-03");
        customer.setEmail("jane.doe@example.org");
        customer.setFathername("Fathername");
        customer.setMobile(987654321L);
        customer.setMothername("Mothername");
        customer.setName("Name");
        customer.setPassword("12345");

        Account account = new Account();
        account.setAccountNo(1234567890L);
        account.setAccountType("3");
        account.setBalance(10.0d);
        account.setBranch("Embassy");
        account.setCustomer(customer);
        account.setDisabled(true);
        account.setIfsc("Ifsc");
        account.setOpeneingDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        account.setTransaction(new ArrayList<>());
        Optional<Account> ofResult = Optional.of(account);
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Customer customer2 = new Customer();
        customer2.setAadhar("123456789");
        customer2.setAccount(new ArrayList<>());
        customer2.setAddress("42 Main St");
        customer2.setCustomerId(1L);
        customer2.setDob("2001-09-03");
        customer2.setEmail("jane.doe@example.org");
        customer2.setFathername("Fathername");
        customer2.setMobile(987654321L);
        customer2.setMothername("Mothername");
        customer2.setName("Name");
        customer2.setPassword("12345");

        Account acc_no = new Account();
        acc_no.setAccountNo(1234567890L);
        acc_no.setAccountType("Savings");
        acc_no.setBalance(10.0d);
        acc_no.setBranch("Embassy");
        acc_no.setCustomer(customer2);
        acc_no.setDisabled(true);
        acc_no.setIfsc("Ifsc");
        acc_no.setOpeneingDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        acc_no.setTransaction(new ArrayList<>());

        Transaction transaction = new Transaction();
        transaction.setAccFrom(1L);
        transaction.setAccTo(1L);
        transaction.setAcc_no(acc_no);
        transaction.setAmount(10.0d);
        transaction.setStatus("Success");
        transaction.setTimestamp(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        transaction.setTransType("NEFT");
        transaction.setTransactionId(1L);
        when(transactionRepository.save(Mockito.<Transaction>any())).thenReturn(transaction);

        Customer customer3 = new Customer();
        customer3.setAadhar("1234567890");
        customer3.setAccount(new ArrayList<>());
        customer3.setAddress("42 Main St");
        customer3.setCustomerId(1L);
        customer3.setDob("2001-09-03");
        customer3.setEmail("jane.doe@example.org");
        customer3.setFathername("Fathername");
        customer3.setMobile(987654321L);
        customer3.setMothername("Mothername");
        customer3.setName("Name");
        customer3.setPassword("12345");

        Account acc_no2 = new Account();
        acc_no2.setAccountNo(1234567890L);
        acc_no2.setAccountType("Savings");
        acc_no2.setBalance(10.0d);
        acc_no2.setBranch("Embassy");
        acc_no2.setCustomer(customer3);
        acc_no2.setDisabled(true);
        acc_no2.setIfsc("Ifsc");
        acc_no2.setOpeneingDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        acc_no2.setTransaction(new ArrayList<>());
        Transaction trans = mock(Transaction.class);
        when(trans.getAmount()).thenReturn(10.0d);
        when(trans.getAccFrom()).thenReturn(0L);
        when(trans.getAccTo()).thenReturn(1L);
        doNothing().when(trans).setAccFrom(anyLong());
        doNothing().when(trans).setAccTo(anyLong());
        doNothing().when(trans).setAcc_no(Mockito.<Account>any());
        doNothing().when(trans).setAmount(anyDouble());
        doNothing().when(trans).setStatus(Mockito.<String>any());
        doNothing().when(trans).setTimestamp(Mockito.<Date>any());
        doNothing().when(trans).setTransType(Mockito.<String>any());
        doNothing().when(trans).setTransactionId(anyLong());
        trans.setAccFrom(1L);
        trans.setAccTo(1L);
        trans.setAcc_no(acc_no2);
        trans.setAmount(10.0d);
        trans.setStatus("Success");
        trans.setTimestamp(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        trans.setTransType("Trans Type");
        trans.setTransactionId(1L);
        assertSame(transaction, transactionService.transact(trans));
        verify(accountRepository, atLeast(1)).findById(Mockito.<Long>any());
        verify(transactionRepository).save(Mockito.<Transaction>any());
        verify(trans).getAmount();
        verify(trans, atLeast(1)).getAccFrom();
        verify(trans, atLeast(1)).getAccTo();
        verify(trans).setAccFrom(anyLong());
        verify(trans).setAccTo(anyLong());
        verify(trans, atLeast(1)).setAcc_no(Mockito.<Account>any());
        verify(trans).setAmount(anyDouble());
        verify(trans, atLeast(1)).setStatus(Mockito.<String>any());
        verify(trans, atLeast(1)).setTimestamp(Mockito.<Date>any());
        verify(trans).setTransType(Mockito.<String>any());
        verify(trans).setTransactionId(anyLong());
    }

    /**
     * Method under test: {@link TransactionService#accountStatement(Date, Date, long)}
     */
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

    /**
     * Method under test: {@link TransactionService#accountStatement(Date, Date, long)}
     */
    @Test
    void testAccountStatement2() throws ParseException {
        when(transactionRepository.findByAccountNumber(anyLong())).thenReturn(new ArrayList<>());
        Date startDate = Date.from(LocalDate.ofYearDay(1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        assertTrue(
                transactionService
                        .accountStatement(startDate,
                                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()), 3L)
                        .isEmpty());
        verify(transactionRepository).findByAccountNumber(anyLong());
    }


    /**
     * Method under test: {@link TransactionService#accountStatement(Date, Date, long)}
     */
    @Test
    void testAccountStatement4() throws ParseException {
        Customer customer = new Customer();
        customer.setAadhar("42");
        customer.setAccount(new ArrayList<>());
        customer.setAddress("17 High St");
        customer.setCustomerId(2L);
        customer.setDob("42");
        customer.setEmail("john.smith@example.org");
        customer.setFathername("42");
        customer.setMobile(0L);
        customer.setMothername("42");
        customer.setName("42");
        customer.setPassword("Password");

        Account acc_no = new Account();
        acc_no.setAccountNo(3L);
        acc_no.setAccountType("Account Type");
        acc_no.setBalance(0.5d);
        acc_no.setBranch("Branch");
        acc_no.setCustomer(customer);
        acc_no.setDisabled(false);
        acc_no.setIfsc("42");
        acc_no.setOpeneingDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        acc_no.setTransaction(new ArrayList<>());

        Transaction transaction = new Transaction();
        transaction.setAccFrom(0L);
        transaction.setAccTo(0L);
        transaction.setAcc_no(acc_no);
        transaction.setAmount(0.5d);
        transaction.setStatus("42");
        transaction.setTimestamp(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        transaction.setTransType("42");
        transaction.setTransactionId(2L);

        ArrayList<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);
        when(transactionRepository.findByAccountNumber(anyLong())).thenReturn(transactionList);
        Date startDate = Date.from(LocalDate.ofYearDay(1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        assertTrue(
                transactionService
                        .accountStatement(startDate,
                                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()), 3L)
                        .isEmpty());
        verify(transactionRepository).findByAccountNumber(anyLong());
    }


    /**
     * Method under test: {@link TransactionService#accountSummary(long)}
     */
    @Test
    void testAccountSummary() {
        when(transactionRepository.findByAccountNumber(anyLong())).thenReturn(new ArrayList<>());
        assertTrue(transactionService.accountSummary(3L).isEmpty());
        verify(transactionRepository).findByAccountNumber(anyLong());
    }

}