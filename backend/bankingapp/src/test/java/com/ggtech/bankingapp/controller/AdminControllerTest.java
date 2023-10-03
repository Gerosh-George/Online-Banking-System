package com.ggtech.bankingapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ggtech.bankingapp.model.AddBalanceRequest;
import com.ggtech.bankingapp.model.Admin;
import com.ggtech.bankingapp.service.AdminService;

import java.util.ArrayList;

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
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {AdminController.class})
@ExtendWith(SpringExtension.class)
class AdminControllerTest {

    @Autowired
    private AdminController adminController;

    @MockBean
    private AdminService adminService;


    @Test
    void testSaveAdmin() throws Exception {
        when(adminService.saveAdmin(Mockito.<Admin>any())).thenReturn("Save Admin");

        Admin admin = new Admin();
        admin.setPassword("password");
        admin.setUserid("1");
        String content = (new ObjectMapper()).writeValueAsString(admin);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Save Admin"));
    }

    @Test
    void testAddFund() throws Exception {
        when(adminService.addCustomerBalance(anyLong(), anyDouble())).thenReturn("Add Customer Balance");

        AddBalanceRequest addBalanceRequest = new AddBalanceRequest();
        addBalanceRequest.setAccno(1L);
        addBalanceRequest.setAmount(10.0d);
        String content = (new ObjectMapper()).writeValueAsString(addBalanceRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/addFund")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Add Customer Balance"));
    }


    @Test
    void testGetAccounts() throws Exception {
        when(adminService.getAccounts()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/accounts");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }





}