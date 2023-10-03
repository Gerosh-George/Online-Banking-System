package com.ggtech.bankingapp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class CustomerTest {



    @Test
    void testCustomerModel() {
        Customer customer = new Customer();
        customer.setAadhar("1234567890");
        ArrayList<Account> account = new ArrayList<>();
        customer.setAccount(account);
        customer.setAddress("42 Main St");
        customer.setDob("2001-09-03");
        customer.setEmail("jane.doe@example.org");
        customer.setFathername("Bob");
        customer.setMobile(987654321L);
        customer.setMothername("Mary");
        customer.setName("Jane");
        customer.setPassword("123456");
        String actualAadhar = customer.getAadhar();
        List<Account> actualAccount = customer.getAccount();
        String actualAddress = customer.getAddress();
        long actualCustomerId = customer.getCustomerId();
        String actualDob = customer.getDob();
        String actualEmail = customer.getEmail();
        String actualFathername = customer.getFathername();
        long actualMobile = customer.getMobile();
        String actualMothername = customer.getMothername();
        String actualName = customer.getName();
        assertEquals("1234567890", actualAadhar);
        assertSame(account, actualAccount);
        assertEquals("42 Main St", actualAddress);
        assertEquals(0L, actualCustomerId);
        assertEquals("2001-09-03", actualDob);
        assertEquals("jane.doe@example.org", actualEmail);
        assertEquals("Bob", actualFathername);
        assertEquals(987654321L, actualMobile);
        assertEquals("Mary", actualMothername);
        assertEquals("Jane", actualName);
        assertEquals("123456", customer.getPassword());
    }
}

