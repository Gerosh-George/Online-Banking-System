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
import org.mockito.Mockito;
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




    /**
     * Method under test: {@link AccountController#fetchTransactions(long)}
     */
    @Test
    void testFetchTransactions() throws Exception {
        when(accountsService.fetchTransactions(anyLong())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/fetchTransactions/{accno}", 1L);
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AccountController#getAccountDetails(long)}
     */
    @Test
    void testGetAccountDetails() throws Exception {
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
        customer.setPassword("abcdefghij");

        Account account = new Account();
        account.setAccountNo(1234567890L);
        account.setAccountType("3");
        account.setBalance(10.0d);
        account.setBranch("Embassy");
        account.setCustomer(customer);
        account.setDisabled(true);
        account.setIfsc("Ifsc");
        account.setOpeneingDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        when(accountsService.getAccountDetails(anyLong())).thenReturn(account);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getAccountDetails/{accno}", 1L);
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"accountNo\":1234567890,\"accountType\":\"3\",\"balance\":10.0,\"branch\":\"Embassy\",\"ifsc\":\"Ifsc"
                                        + "\",\"openeingDate\":[1970,1,1,0,0],\"customer\":{\"customerId\":1,\"name\":\"Name\",\"password\":\"abcdefghij\",\"email"
                                        + "\":\"jane.doe@example.org\",\"mobile\":1,\"aadhar\":\"Aadhar\",\"dob\":\"Dob\",\"address\":\"42 Main St\",\"fathername"
                                        + "\":\"Fathername\",\"mothername\":\"Mothername\",\"account\":[]},\"disabled\":true}"));
    }
}

