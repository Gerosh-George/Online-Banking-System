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

    /**
     * Method under test: {@link AdminController#saveAdmin(Admin)}
     */
    @Test
    void testSaveAdmin() throws Exception {
        when(adminService.saveAdmin(Mockito.<Admin>any())).thenReturn("Save Admin");

        Admin admin = new Admin();
        admin.setPassword("iloveyou");
        admin.setUserid("Userid");
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

    /**
     * Method under test: {@link AdminController#addFund(AddBalanceRequest)}
     */
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

    /**
     * Method under test: {@link AdminController#getAccounts()}
     */
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

    /**
     * Method under test: {@link AdminController#getAccounts()}
     */
    @Test
    void testGetAccounts2() throws Exception {
        when(adminService.getAccounts()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/accounts");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AdminController#adminLogin(Admin)}
     */
    @Test
    void testAdminLogin() throws Exception {
        when(adminService.login(Mockito.<Admin>any())).thenReturn("Login");

        Admin admin = new Admin();
        admin.setPassword("iloveyou");
        admin.setUserid("Userid");
        String content = (new ObjectMapper()).writeValueAsString(admin);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Login"));
    }

    /**
     * Method under test: {@link AdminController#toggleDisable(long)}
     */
    @Test
    void testToggleDisable() throws Exception {
        when(adminService.toggleAccountStatus(anyLong())).thenReturn("3");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/toggleAccountStatus/{accno}",
                1L);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("3"));
    }

    /**
     * Method under test: {@link AdminController#updateBalance(AddBalanceRequest)}
     */
    @Test
    void testUpdateBalance() throws Exception {
        when(adminService.updateCustomerBalance(anyLong(), anyDouble())).thenReturn("2020-03-01");

        AddBalanceRequest addBalanceRequest = new AddBalanceRequest();
        addBalanceRequest.setAccno(1L);
        addBalanceRequest.setAmount(10.0d);
        String content = (new ObjectMapper()).writeValueAsString(addBalanceRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/updateBalance")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("2020-03-01"));
    }

}