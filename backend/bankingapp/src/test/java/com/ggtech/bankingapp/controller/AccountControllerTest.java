package com.ggtech.bankingapp.controller;

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ggtech.bankingapp.model.Account;
import com.ggtech.bankingapp.model.Customer;
import com.ggtech.bankingapp.service.AccountsService;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AccountController.class})
@ExtendWith(SpringExtension.class)
class AccountControllerTest {
    @Autowired
    private AccountController accountController;

    @MockBean
    private AccountsService accountsService;


    @Test
    void testFetchTransactions() throws Exception {
        when(accountsService.fetchTransactions(anyLong())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/transactions/{accno}", 1L);
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testGetAccountDetails() throws Exception {
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
        account.setAccountType("SAVINGS");
        account.setBalance(10.0d);
        account.setBranch("BLR");
        account.setCustomer(customer);
        account.setDisabled(true);
        account.setIfsc("12345");
        account.setOpeneingDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        account.setTransaction(new ArrayList<>());
        when(accountsService.getAccountDetails(anyLong())).thenReturn(account);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getAccountDetails/{accno}", 1L);
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"accountNo\":1234567890,\"accountType\":\"SAVINGS\",\"balance\":10.0,\"branch\":\"BLR\",\"ifsc\":\"12345"
                                        + "\",\"openeingDate\":[1970,1,1,0,0],\"transaction\":[],\"disabled\":true}"));
    }


    @Test
    void testCheckBalance() throws Exception {
        when(accountsService.checkBalance(anyLong())).thenReturn(10.0d);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/checkBalance/{accno}", 1L);
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("10.0"));
    }




    @Test
    void testGetAllAccountBalance() throws Exception {
        when(accountsService.getAllAccountBalance(anyLong())).thenReturn(10.0d);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/allAccountBalance/{cid}", 1L);
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("10.0"));
    }



    @Test
    void testGetAccount() throws Exception {
        when(accountsService.getUserAccounts(anyLong())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/account/{uid}", 1L);
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

