package com.ggtech.bankingapp.service;

import com.ggtech.bankingapp.exceptions.NoDataFoundException;
import com.ggtech.bankingapp.exceptions.ResourceNotFoundException;
import com.ggtech.bankingapp.model.Account;
import com.ggtech.bankingapp.model.Customer;
import com.ggtech.bankingapp.model.LoginRequest;
import com.ggtech.bankingapp.repository.AccountRepository;
import com.ggtech.bankingapp.repository.CustomerRepository;
import com.ggtech.bankingapp.repository.TransactionRepository;

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

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {CustomerService.class})
@ExtendWith(SpringExtension.class)
class CustomerServiceTest {

    @MockBean
    private TransactionRepository transactionRepository;

    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @Test
    void testGetAllCustomerList() {
        ArrayList<Customer> customerList = new ArrayList<>();
        when(customerRepository.findAll()).thenReturn(customerList);
        List<Customer> actualAllCustomerList = customerService.getAllCustomerList();
        assertSame(customerList, actualAllCustomerList);
        assertTrue(actualAllCustomerList.isEmpty());
        verify(customerRepository).findAll();
    }



    @Test
    void testSaveCustomer() {
        Customer customer = new Customer();
        customer.setAadhar("123456789012");
        customer.setAccount(new ArrayList<>());
        customer.setAddress("42 Main St");
        customer.setCustomerId(1L);
        customer.setDob("03-09-2001");
        customer.setEmail("jane.doe@example.org");
        customer.setFathername("Bob");
        customer.setMobile(987654321L);
        customer.setMothername("Mary");
        customer.setName("Jane");
        customer.setPassword("password");
        when(customerRepository.findByAadhar(Mockito.<String>any())).thenReturn(customer);

        Customer cust = new Customer();
        cust.setAadhar("123456789012");
        cust.setAccount(new ArrayList<>());
        cust.setAddress("42 Main St");
        cust.setCustomerId(1L);
        cust.setDob("03-09-2001");
        cust.setEmail("jane.doe@example.org");
        cust.setFathername("Bob");
        cust.setMobile(987654321L);
        cust.setMothername("Mary");
        cust.setName("Jane");
        cust.setPassword("password");
        assertEquals("{\"message\":\"Customer already exists!\",\"id\":\"0\"}", customerService.saveCustomer(cust));
        verify(customerRepository).findByAadhar(Mockito.<String>any());
    }

    @Test
    void testValidateCustomer() throws NoDataFoundException {
        Customer customer = new Customer();
        customer.setAadhar("123456789012");
        customer.setAccount(new ArrayList<>());
        customer.setAddress("42 Main St");
        customer.setCustomerId(1L);
        customer.setDob("03-09-2001");
        customer.setEmail("jane.doe@example.org");
        customer.setFathername("Bob");
        customer.setMobile(987654321);
        customer.setMothername("Mary");
        customer.setName("Jane");
        customer.setPassword("password");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        LoginRequest u = new LoginRequest();
        u.setCustomerId(1L);
        u.setPassword("password");
        assertEquals("Login success", customerService.validateCustomer(u));
        verify(customerRepository).findById(Mockito.<Long>any());
    }


    @Test
    void testResetPassword() throws ResourceNotFoundException {
        Customer customer = new Customer();
        customer.setAadhar("123456789012");
        customer.setAccount(new ArrayList<>());
        customer.setAddress("42 Main St");
        customer.setCustomerId(1L);
        customer.setDob("03-09-2001");
        customer.setEmail("jane.doe@example.org");
        customer.setFathername("Bob");
        customer.setMobile(987654321L);
        customer.setMothername("Mary");
        customer.setName("Jane");
        customer.setPassword("password");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        LoginRequest u = new LoginRequest();
        u.setCustomerId(1L);
        u.setPassword("password");
        assertEquals("Invalid OTP", customerService.resetPassword(u, "Otp"));
        verify(customerRepository).findById(Mockito.<Long>any());
    }



    @Test
    void testGetAllUserTransactions() throws ResourceNotFoundException {
        Customer customer = new Customer();
        customer.setAadhar("123456789012");
        customer.setAccount(new ArrayList<>());
        customer.setAddress("42 Main St");
        customer.setCustomerId(1L);
        customer.setDob("03-09-2001");
        customer.setEmail("jane.doe@example.org");
        customer.setFathername("Bob");
        customer.setMobile(987654321L);
        customer.setMothername("Mary");
        customer.setName("Jane");
        customer.setPassword("password");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertTrue(customerService.getAllUserTransactions(1L).isEmpty());
        verify(customerRepository).findById(Mockito.<Long>any());
    }



}