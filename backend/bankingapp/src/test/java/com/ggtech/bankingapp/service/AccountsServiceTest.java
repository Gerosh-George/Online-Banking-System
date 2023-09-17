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


    /**
     * Method under test: {@link AccountsService#createAccount(Account, Long)}
     */
    @Test
    void testCreateAccount() {
        Customer customer = new Customer();
        customer.setAadhar("Aadhar");
        customer.setAccount(new ArrayList<>());
        customer.setAddress("42 Main St");
        customer.setCustomerId(1L);
        customer.setDob("Dob");
        customer.setEmail("jane.doe@example.org");
        customer.setFathername("Fathername");
        customer.setMobile(1L);
        customer.setMothername("Mothername");
        customer.setName("Name");
        customer.setPassword("password");

        Account account = new Account();
        account.setAccountNo(1234567890L);
        account.setAccountType("3");
        account.setBalance(10.0d);
        account.setBranch("embassy");
        account.setCustomer(customer);
        account.setDisabled(true);
        account.setIfsc("Ifsc");
        account.setOpeneingDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        when(accountRepository.save(Mockito.<Account>any())).thenReturn(account);
        Optional<Account> emptyResult = Optional.empty();
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        Customer customer2 = new Customer();
        customer2.setAadhar("Aadhar");
        customer2.setAccount(new ArrayList<>());
        customer2.setAddress("42 Main St");
        customer2.setCustomerId(1L);
        customer2.setDob("Dob");
        customer2.setEmail("jane.doe@example.org");
        customer2.setFathername("Fathername");
        customer2.setMobile(1L);
        customer2.setMothername("Mothername");
        customer2.setName("Name");
        customer2.setPassword("password");
        Optional<Customer> ofResult = Optional.of(customer2);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Customer customer3 = new Customer();
        customer3.setAadhar("Aadhar");
        customer3.setAccount(new ArrayList<>());
        customer3.setAddress("42 Main St");
        customer3.setCustomerId(1L);
        customer3.setDob("Dob");
        customer3.setEmail("jane.doe@example.org");
        customer3.setFathername("Fathername");
        customer3.setMobile(1L);
        customer3.setMothername("Mothername");
        customer3.setName("Name");
        customer3.setPassword("password");

        Account account2 = new Account();
        account2.setAccountNo(1234567890L);
        account2.setAccountType("3");
        account2.setBalance(10.0d);
        account2.setBranch("embassy");
        account2.setCustomer(customer3);
        account2.setDisabled(true);
        account2.setIfsc("Ifsc");
        account2.setOpeneingDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        assertSame(account, accountsService.createAccount(account2, 1L));
        verify(accountRepository).save(Mockito.<Account>any());
        verify(accountRepository).findById(Mockito.<Long>any());
        verify(customerRepository).findById(Mockito.<Long>any());
        assertFalse(account2.isDisabled());

        assertSame(customer2, account2.getCustomer());
    }

    /**
     * Method under test: {@link AccountsService#fetchTransactions(long)}
     */
    @Test
    void testFetchTransactions() throws NoDataFoundException {
        when(transactionRepository.findByAccountNumber(anyLong())).thenReturn(new ArrayList<>());
        assertThrows(NoDataFoundException.class, () -> accountsService.fetchTransactions(1L));
        verify(transactionRepository).findByAccountNumber(anyLong());
    }

    /**
     * Method under test: {@link AccountsService#fetchTransactions(long)}
     */
    @Test
    void testFetchTransactions2() throws NoDataFoundException {
        Customer customer = new Customer();
        customer.setAadhar("SUCCESS");
        customer.setAccount(new ArrayList<>());
        customer.setAddress("42 Main St");
        customer.setCustomerId(1L);
        customer.setDob("SUCCESS");
        customer.setEmail("jane.doe@example.org");
        customer.setFathername("SUCCESS");
        customer.setMobile(1L);
        customer.setMothername("SUCCESS");
        customer.setName("SUCCESS");
        customer.setPassword("password");

        Account acc_no = new Account();
        acc_no.setAccountNo(1234567890L);
        acc_no.setAccountType("3");
        acc_no.setBalance(10.0d);
        acc_no.setBranch("Embassy");
        acc_no.setCustomer(customer);
        acc_no.setDisabled(true);
        acc_no.setIfsc("SUCCESS");
        acc_no.setOpeneingDate(LocalDate.of(1970, 1, 1).atStartOfDay());

        Transaction transaction = new Transaction();
        transaction.setAccFrom(1L);
        transaction.setAccTo(1L);
        transaction.setAcc_no(acc_no);
        transaction.setAmount(10.0d);
        transaction.setStatus("SUCCESS");
        Timestamp timeStamp=new Timestamp(System.currentTimeMillis());
        transaction.setTimestamp(timeStamp);
        transaction.setTransType("SUCCESS");
        transaction.setTransactionId(1L);

        ArrayList<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);
        when(transactionRepository.findByAccountNumber(anyLong())).thenReturn(transactionList);
        List<Transaction> actualFetchTransactionsResult = accountsService.fetchTransactions(1L);
        assertSame(transactionList, actualFetchTransactionsResult);
        assertEquals(1, actualFetchTransactionsResult.size());
        verify(transactionRepository, atLeast(1)).findByAccountNumber(anyLong());
    }

    /**
     * Method under test: {@link AccountsService#getAccountDetails(long)}
     */
    @Test
    void testGetAccountDetails() throws ResourceNotFoundException {
        Customer customer = new Customer();
        customer.setAadhar("Aadhar");
        customer.setAccount(new ArrayList<>());
        customer.setAddress("42 Main St");
        customer.setCustomerId(1L);
        customer.setDob("Dob");
        customer.setEmail("jane.doe@example.org");
        customer.setFathername("Fathername");
        customer.setMobile(1L);
        customer.setMothername("Mothername");
        customer.setName("Name");
        customer.setPassword("password");

        Account account = new Account();
        account.setAccountNo(1234567890L);
        account.setAccountType("3");
        account.setBalance(10.0d);
        account.setBranch("Embassy");
        account.setCustomer(customer);
        account.setDisabled(true);
        account.setIfsc("Ifsc");
        account.setOpeneingDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        Optional<Account> ofResult = Optional.of(account);
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertSame(account, accountsService.getAccountDetails(3L));
        verify(accountRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AccountsService#getAccountDetails(long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAccountDetails2() throws ResourceNotFoundException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Optional.orElse(Object)" because the return value of "com.ggtech.bankingapp.repository.AccountRepository.findById(Object)" is null
        //       at com.ggtech.bankingapp.service.AccountsService.getAccountDetails(AccountsService.java:60)
        //   See https://diff.blue/R013 to resolve this issue.

        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(null);
        accountsService.getAccountDetails(3L);
    }

    /**
     * Method under test: {@link AccountsService#getAccountDetails(long)}
     */
    @Test
    void testGetAccountDetails3() throws ResourceNotFoundException {
        Optional<Account> emptyResult = Optional.empty();
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        assertThrows(ResourceNotFoundException.class, () -> accountsService.getAccountDetails(3L));
        verify(accountRepository).findById(Mockito.<Long>any());
    }
}

