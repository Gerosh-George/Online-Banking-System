package com.ggtech.bankingapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ggtech.bankingapp.model.Account;
import com.ggtech.bankingapp.model.AccountStatement;
import com.ggtech.bankingapp.model.Customer;
import com.ggtech.bankingapp.model.Transaction;
import com.ggtech.bankingapp.service.TransactionService;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;

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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {TransactionController.class})
@ExtendWith(SpringExtension.class)
class TransactionControllerTest {

    @Autowired
    private TransactionController transactionController;

    @MockBean
    private TransactionService transactionService;

    /**
     * Method under test: {@link TransactionController#accountStatement(long, AccountStatement)}
     */
    @Test
    void testAccountStatement() throws Exception {
        when(transactionService.accountStatement(Mockito.<Date>any(), Mockito.<Date>any(), anyLong()))
                .thenReturn(new ArrayList<>());

        AccountStatement accountStatement = new AccountStatement();
        accountStatement
                .setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        accountStatement
                .setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        String content = (new ObjectMapper()).writeValueAsString(accountStatement);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/accountStatement/{accno}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TransactionController#accountSummary(long)}
     */
    @Test
    void testAccountSummary() throws Exception {
        when(transactionService.accountSummary(anyLong())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/accountSummary/{accno}", 1L);
        MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TransactionController#accountSummary(long)}
     */
    @Test
    void testAccountSummary2() throws Exception {
        when(transactionService.accountSummary(anyLong())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/accountSummary/{accno}", 1L);
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TransactionController#Transact(Transaction)}
     */
    @Test
    void testTransact() throws Exception {
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

        Account acc_no = new Account();
        acc_no.setAccountNo(1234567890L);
        acc_no.setAccountType("3");
        acc_no.setBalance(10.0d);
        acc_no.setBranch("janedoe/featurebranch");
        acc_no.setCustomer(customer);
        acc_no.setDisabled(true);
        acc_no.setIfsc("Ifsc");
        acc_no.setOpeneingDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        acc_no.setTransaction(new ArrayList<>());

        Transaction transaction = new Transaction();
        transaction.setAccFrom(1L);
        transaction.setAccTo(1L);
        transaction.setAcc_no(acc_no);
        transaction.setAmount(10.0d);
        transaction.setStatus("Status");
        transaction.setTimestamp(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        transaction.setTransType("Trans Type");
        transaction.setTransactionId(1L);
        when(transactionService.transact(Mockito.<Transaction>any())).thenReturn(transaction);

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

        Account acc_no2 = new Account();
        acc_no2.setAccountNo(1234567890L);
        acc_no2.setAccountType("3");
        acc_no2.setBalance(10.0d);
        acc_no2.setBranch("janedoe/featurebranch");
        acc_no2.setCustomer(customer2);
        acc_no2.setDisabled(true);
        acc_no2.setIfsc("Ifsc");
        acc_no2.setOpeneingDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        acc_no2.setTransaction(new ArrayList<>());

        Transaction transaction2 = new Transaction();
        transaction2.setAccFrom(1L);
        transaction2.setAccTo(1L);
        transaction2.setAcc_no(acc_no2);
        transaction2.setAmount(10.0d);
        transaction2.setStatus("Status");
        transaction2.setTimestamp(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        transaction2.setTransType("Trans Type");
        transaction2.setTransactionId(1L);
        String content = (new ObjectMapper()).writeValueAsString(transaction2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/transact")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Transaction Success"));
    }

}