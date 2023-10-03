package com.ggtech.bankingapp.controller;

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ggtech.bankingapp.model.Customer;
import com.ggtech.bankingapp.model.LoginRequest;
import com.ggtech.bankingapp.service.CustomerService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CustomerController.class})
@ExtendWith(SpringExtension.class)
class CustomerControllerTest {
    @Autowired
    private CustomerController customerController;

    @MockBean
    private CustomerService customerService;


    @Test
    void testValidateCustomer() throws Exception {
        when(customerService.validateCustomer(Mockito.<LoginRequest>any())).thenReturn("2020-03-01");

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setCustomerId(1L);
        loginRequest.setPassword("password");
        String content = (new ObjectMapper()).writeValueAsString(loginRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("2020-03-01"));
    }

    @Test
    void testChangeDetails() throws Exception {
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
        String content = (new ObjectMapper()).writeValueAsString(customer);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/changeDetails")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }


    @Test
    void testGetCustomer() throws Exception {
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
        when(customerService.getCustomer(anyLong())).thenReturn(customer);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customer/{cid}", 1L);
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"customerId\":1,\"name\":\"Jane\",\"password\":\"password\",\"email\":\"jane.doe@example.org\",\"mobile\":987654321,\"aadhar"
                                        + "\":\"123456789012\",\"dob\":\"03-09-2001\",\"address\":\"42 Main St\",\"fathername\":\"Bob\",\"mothername\":\"Mary\","
                                        + "\"account\":[]}"));
    }



    @Test
    void testGetAllTransactions() throws Exception {
        when(customerService.getAllUserTransactions(anyLong())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/allTransactions/{cid}", 1L);
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}