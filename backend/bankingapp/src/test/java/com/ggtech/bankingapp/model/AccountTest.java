package com.ggtech.bankingapp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class AccountTest {

    @Test
    void testAccountModel() {
        Account account = new Account();
        account.setAccountNo(1234567890L);
        account.setAccountType("Savings");
        account.setBalance(1000.0d);
        account.setBranch("Embassy");
        Customer customer = new Customer();
        customer.setAadhar("123456789");
        customer.setAccount(new ArrayList<>());
        customer.setAddress("42 Main St");
        customer.setCustomerId(1L);
        customer.setDob("03-09-2001");
        customer.setEmail("jane.doe@example.org");
        customer.setFathername("Bob");
        customer.setMobile(987654321);
        customer.setMothername("Mary");
        customer.setName("Jane");
        customer.setPassword("12345");
        account.setCustomer(customer);
        account.setDisabled(true);
        account.setIfsc("12345");
        LocalDateTime openeingDate = LocalDate.of(1970, 1, 1).atStartOfDay();
        account.setOpeneingDate(openeingDate);
        ArrayList<Transaction> transaction = new ArrayList<>();
        account.setTransaction(transaction);
        long actualAccountNo = account.getAccountNo();
        String actualAccountType = account.getAccountType();
        double actualBalance = account.getBalance();
        String actualBranch = account.getBranch();
        Customer actualCustomer = account.getCustomer();
        String actualIfsc = account.getIfsc();
        LocalDateTime actualOpeneingDate = account.getOpeneingDate();
        List<Transaction> actualTransaction = account.getTransaction();
        boolean actualIsDisabledResult = account.isDisabled();
        assertEquals(1234567890L, actualAccountNo);
        assertEquals("Savings", actualAccountType);
        assertEquals(1000.0d, actualBalance);
        assertEquals("Embassy", actualBranch);
        assertSame(customer, actualCustomer);
        assertEquals("12345", actualIfsc);
        assertSame(openeingDate, actualOpeneingDate);
        assertSame(transaction, actualTransaction);
        assertEquals(actualCustomer.getAccount(), actualTransaction);
        assertTrue(actualIsDisabledResult);
    }
}

