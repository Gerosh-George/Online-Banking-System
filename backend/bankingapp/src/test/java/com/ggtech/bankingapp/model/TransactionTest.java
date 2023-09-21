package com.ggtech.bankingapp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;

class TransactionTest {

    @Test
    void testSetAccFrom() {
        Transaction transaction = new Transaction();
        transaction.setAccFrom(1L);
        transaction.setAccTo(1L);
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
        acc_no.setAccountType("Savings");
        acc_no.setBalance(10.0d);
        acc_no.setBranch("janedoe/featurebranch");
        acc_no.setCustomer(customer);
        acc_no.setDisabled(true);
        acc_no.setIfsc("Ifsc");
        acc_no.setOpeneingDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        acc_no.setTransaction(new ArrayList<>());
        transaction.setAcc_no(acc_no);
        transaction.setAmount(10.0d);
        transaction.setStatus("Status");
        Date timestamp = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        transaction.setTimestamp(timestamp);
        transaction.setTransType("NEFT");
        transaction.setTransactionId(1L);
        long actualAccFrom = transaction.getAccFrom();
        long actualAccTo = transaction.getAccTo();
        Account actualAcc_no = transaction.getAcc_no();
        double actualAmount = transaction.getAmount();
        String actualStatus = transaction.getStatus();
        Date actualTimestamp = transaction.getTimestamp();
        String actualTransType = transaction.getTransType();
        assertEquals(1L, actualAccFrom);
        assertEquals(1L, actualAccTo);
        assertSame(acc_no, actualAcc_no);
        assertEquals(10.0d, actualAmount);
        assertEquals("Status", actualStatus);
        assertSame(timestamp, actualTimestamp);
        assertEquals("NEFT", actualTransType);
        assertEquals(1L, transaction.getTransactionId());
    }
}

