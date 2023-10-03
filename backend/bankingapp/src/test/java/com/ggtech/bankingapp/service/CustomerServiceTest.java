package com.ggtech.bankingapp.service;

import com.ggtech.bankingapp.exceptions.NoDataFoundException;
import com.ggtech.bankingapp.exceptions.ResourceNotFoundException;
import com.ggtech.bankingapp.model.Account;
import com.ggtech.bankingapp.model.Customer;
import com.ggtech.bankingapp.model.LoginRequest;
import com.ggtech.bankingapp.repository.AccountRepository;
import com.ggtech.bankingapp.repository.CustomerRepository;

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
    private AccountRepository accountRepository;

    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    /**
     * Method under test: {@link CustomerService#getAllCustomerList()}
     */
    @Test
    void testGetAllCustomerList() {
        ArrayList<Customer> customerList = new ArrayList<>();
        when(customerRepository.findAll()).thenReturn(customerList);
        List<Customer> actualAllCustomerList = customerService.getAllCustomerList();
        assertSame(customerList, actualAllCustomerList);
        assertTrue(actualAllCustomerList.isEmpty());
        verify(customerRepository).findAll();
    }

    /**
     * Method under test: {@link CustomerService#saveCustomer(Customer)}
     */
    @Test
    void testSaveCustomer() {
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
        customer.setPassword("iloveyou");
        when(customerRepository.findByAadhar(Mockito.<String>any())).thenReturn(customer);

        Customer cust = new Customer();
        cust.setAadhar("Aadhar");
        cust.setAccount(new ArrayList<>());
        cust.setAddress("42 Main St");
        cust.setCustomerId(1L);
        cust.setDob("Dob");
        cust.setEmail("jane.doe@example.org");
        cust.setFathername("Fathername");
        cust.setMobile(1L);
        cust.setMothername("Mothername");
        cust.setName("Name");
        cust.setPassword("iloveyou");
        assertEquals("Customer already exists!", customerService.saveCustomer(cust));
        verify(customerRepository).findByAadhar(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CustomerService#validateCustomer(LoginRequest)}
     */
    @Test
    void testValidateCustomer() throws NoDataFoundException {
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
        customer.setPassword("iloveyou");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        LoginRequest u = new LoginRequest();
        u.setCustomerId(1L);
        u.setPassword("iloveyou");
        assertEquals("Login success", customerService.validateCustomer(u));
        verify(customerRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CustomerService#validateCustomer(LoginRequest)}
     */
    @Test
    void testValidateCustomer2() throws NoDataFoundException {
        Customer customer = mock(Customer.class);
        when(customer.getPassword()).thenReturn("foo");
        doNothing().when(customer).setAadhar(Mockito.<String>any());
        doNothing().when(customer).setAccount(Mockito.<List<Account>>any());
        doNothing().when(customer).setAddress(Mockito.<String>any());
        doNothing().when(customer).setCustomerId(Mockito.<Long>any());
        doNothing().when(customer).setDob(Mockito.<String>any());
        doNothing().when(customer).setEmail(Mockito.<String>any());
        doNothing().when(customer).setFathername(Mockito.<String>any());
        doNothing().when(customer).setMobile(anyLong());
        doNothing().when(customer).setMothername(Mockito.<String>any());
        doNothing().when(customer).setName(Mockito.<String>any());
        doNothing().when(customer).setPassword(Mockito.<String>any());
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
        customer.setPassword("iloveyou");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        LoginRequest u = new LoginRequest();
        u.setCustomerId(1L);
        u.setPassword("iloveyou");
        assertEquals("Login failed", customerService.validateCustomer(u));
        verify(customerRepository).findById(Mockito.<Long>any());
        verify(customer).getPassword();
        verify(customer).setAadhar(Mockito.<String>any());
        verify(customer).setAccount(Mockito.<List<Account>>any());
        verify(customer).setAddress(Mockito.<String>any());
        verify(customer).setCustomerId(Mockito.<Long>any());
        verify(customer).setDob(Mockito.<String>any());
        verify(customer).setEmail(Mockito.<String>any());
        verify(customer).setFathername(Mockito.<String>any());
        verify(customer).setMobile(anyLong());
        verify(customer).setMothername(Mockito.<String>any());
        verify(customer).setName(Mockito.<String>any());
        verify(customer).setPassword(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CustomerService#validateCustomer(LoginRequest)}
     */
    @Test
    void testValidateCustomer3() throws NoDataFoundException {
        Optional<Customer> emptyResult = Optional.empty();
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        LoginRequest u = new LoginRequest();
        u.setCustomerId(1L);
        u.setPassword("iloveyou");
        assertEquals("Invalid Customer", customerService.validateCustomer(u));
        verify(customerRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CustomerService#getCustomer(long)}
     */
    @Test
    void testGetCustomer() throws ResourceNotFoundException {
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
        customer.setPassword("iloveyou");

        Account account = new Account();
        account.setAccountNo(1234567890L);
        account.setAccountType("3");
        account.setBalance(10.0d);
        account.setBranch("janedoe/featurebranch");
        account.setCustomer(customer);
        account.setDisabled(true);
        account.setIfsc("Ifsc");
        account.setOpeneingDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        account.setTransaction(new ArrayList<>());
        Optional<Account> ofResult = Optional.of(account);
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

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
        customer2.setPassword("iloveyou");
        Optional<Customer> ofResult2 = Optional.of(customer2);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);
        assertSame(customer2, customerService.getCustomer(1L));
        verify(customerRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CustomerService#getCustomer(long)}
     */
    @Test
    void testGetCustomer3() throws ResourceNotFoundException {
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
        customer.setPassword("iloveyou");

        Account account = new Account();
        account.setAccountNo(1234567890L);
        account.setAccountType("3");
        account.setBalance(10.0d);
        account.setBranch("janedoe/featurebranch");
        account.setCustomer(customer);
        account.setDisabled(true);
        account.setIfsc("Ifsc");
        account.setOpeneingDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        account.setTransaction(new ArrayList<>());
        Optional<Account> ofResult = Optional.of(account);
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Optional<Customer> emptyResult = Optional.empty();
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        assertSame(customer, customerService.getCustomer(1L));
        verify(accountRepository).findById(Mockito.<Long>any());
        verify(customerRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CustomerService#resetPassword(LoginRequest, String)}
     */
    @Test
    void testResetPassword() throws ResourceNotFoundException {
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
        customer.setPassword("iloveyou");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        LoginRequest u = new LoginRequest();
        u.setCustomerId(1L);
        u.setPassword("iloveyou");
        assertEquals("Invalid OTP", customerService.resetPassword(u, "Otp"));
        verify(customerRepository).findById(Mockito.<Long>any());
    }
    /**
     * Method under test: {@link CustomerService#resetPassword(LoginRequest, String)}
     */
    @Test
    void testResetPassword3() throws ResourceNotFoundException {
        Optional<Customer> emptyResult = Optional.empty();
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        LoginRequest u = new LoginRequest();
        u.setCustomerId(1L);
        u.setPassword("iloveyou");
        assertEquals("Invalid customer", customerService.resetPassword(u, "Otp"));
        verify(customerRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CustomerService#resetPassword(LoginRequest, String)}
     */
    @Test
    void testResetPassword4() throws ResourceNotFoundException {
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
        customer.setPassword("iloveyou");
        Optional<Customer> ofResult = Optional.of(customer);

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
        customer2.setPassword("iloveyou");
        when(customerRepository.save(Mockito.<Customer>any())).thenReturn(customer2);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        LoginRequest u = new LoginRequest();
        u.setCustomerId(1L);
        u.setPassword("iloveyou");
        assertEquals("Success!", customerService.resetPassword(u, "101010"));
        verify(customerRepository).save(Mockito.<Customer>any());
        verify(customerRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CustomerService#updateCustomerDetails(Customer)}
     */
    @Test
    void testUpdateCustomerDetails() {
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
        customer.setPassword("iloveyou");
        Optional<Customer> ofResult = Optional.of(customer);

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
        customer2.setPassword("iloveyou");
        when(customerRepository.save(Mockito.<Customer>any())).thenReturn(customer2);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Customer u = new Customer();
        u.setAadhar("Aadhar");
        u.setAccount(new ArrayList<>());
        u.setAddress("42 Main St");
        u.setCustomerId(1L);
        u.setDob("Dob");
        u.setEmail("jane.doe@example.org");
        u.setFathername("Fathername");
        u.setMobile(1L);
        u.setMothername("Mothername");
        u.setName("Name");
        u.setPassword("iloveyou");
        assertEquals("Success!", customerService.updateCustomerDetails(u));
        verify(customerRepository).save(Mockito.<Customer>any());
        verify(customerRepository).findById(Mockito.<Long>any());
    }


    /**
     * Method under test: {@link CustomerService#updateCustomerDetails(Customer)}
     */
    @Test
    void testUpdateCustomerDetails3() {
        Optional<Customer> emptyResult = Optional.empty();
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        Customer u = new Customer();
        u.setAadhar("Aadhar");
        u.setAccount(new ArrayList<>());
        u.setAddress("42 Main St");
        u.setCustomerId(1L);
        u.setDob("Dob");
        u.setEmail("jane.doe@example.org");
        u.setFathername("Fathername");
        u.setMobile(1L);
        u.setMothername("Mothername");
        u.setName("Name");
        u.setPassword("iloveyou");
        assertEquals("Invalid Customer", customerService.updateCustomerDetails(u));
        verify(customerRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CustomerService#getAllUserTransactions(long)}
     */
    @Test
    void testGetAllUserTransactions() throws ResourceNotFoundException {
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
        customer.setPassword("iloveyou");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertTrue(customerService.getAllUserTransactions(1L).isEmpty());
        verify(customerRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CustomerService#getAllUserTransactions(long)}
     */
    @Test
    void testGetAllUserTransactions2() throws ResourceNotFoundException {
        Customer customer = new Customer();
        customer.setAadhar("Aadhar");
        customer.setAccount(new ArrayList<>());
        customer.setAddress("42 Main St");
        customer.setCustomerId(1L);
        customer.setDob("Dob");
        customer.setEmail("jane.doe@example.org");
        customer.setFathername("Fathername");
        customer.setMobile(5L);
        customer.setMothername("Mothername");
        customer.setName("Name");
        customer.setPassword("iloveyou");

        Account account = new Account();
        account.setAccountNo(1234567890L);
        account.setAccountType("3");
        account.setBalance(10.0d);
        account.setBranch("janedoe/featurebranch");
        account.setCustomer(customer);
        account.setDisabled(true);
        account.setIfsc("Ifsc");
        account.setOpeneingDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        account.setTransaction(new ArrayList<>());

        ArrayList<Account> account2 = new ArrayList<>();
        account2.add(account);

        Customer customer2 = new Customer();
        customer2.setAadhar("Aadhar");
        customer2.setAccount(account2);
        customer2.setAddress("42 Main St");
        customer2.setCustomerId(1L);
        customer2.setDob("Dob");
        customer2.setEmail("jane.doe@example.org");
        customer2.setFathername("Fathername");
        customer2.setMobile(1L);
        customer2.setMothername("Mothername");
        customer2.setName("Name");
        customer2.setPassword("iloveyou");
        Optional<Customer> ofResult = Optional.of(customer2);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertTrue(customerService.getAllUserTransactions(1L).isEmpty());
        verify(customerRepository).findById(Mockito.<Long>any());
    }


    /**
     * Method under test: {@link CustomerService#getAllUserTransactions(long)}
     */
    @Test
    void testGetAllUserTransactions4() throws ResourceNotFoundException {
        Optional<Customer> emptyResult = Optional.empty();
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        assertThrows(ResourceNotFoundException.class, () -> customerService.getAllUserTransactions(1L));
        verify(customerRepository).findById(Mockito.<Long>any());
    }

}