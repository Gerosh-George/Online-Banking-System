package com.ggtech.bankingapp.service;

import com.ggtech.bankingapp.exceptions.BalanceInsufficientException;
import com.ggtech.bankingapp.exceptions.NoDataFoundException;
import com.ggtech.bankingapp.model.Account;
import com.ggtech.bankingapp.model.Admin;
import com.ggtech.bankingapp.model.Customer;
import com.ggtech.bankingapp.model.Transaction;
import com.ggtech.bankingapp.repository.AccountRepository;
import com.ggtech.bankingapp.repository.AdminRepository;
import com.ggtech.bankingapp.repository.CustomerRepository;
import com.ggtech.bankingapp.repository.TransactionRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {AdminService.class})
@ExtendWith(SpringExtension.class)
class AdminServiceTest {

    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private AdminRepository adminRepository;

    @Autowired
    private AdminService adminService;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private TransactionRepository transactionRepository;


    @Test
    void testSaveAdmin() {
        Admin admin = new Admin();
        admin.setPassword("password");
        admin.setUserid("1234");
        Optional<Admin> ofResult = Optional.of(admin);
        when(adminRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        Admin admin2 = new Admin();
        admin2.setPassword("password");
        admin2.setUserid("1234");
        assertEquals("Admin already exists!", adminService.saveAdmin(admin2));
        verify(adminRepository).findById(Mockito.<String>any());
    }

    @Test
    void testSaveAdmin2() {
        Admin admin = new Admin();
        admin.setPassword("password");
        admin.setUserid("12345");
        when(adminRepository.save(Mockito.<Admin>any())).thenReturn(admin);
        Optional<Admin> emptyResult = Optional.empty();
        when(adminRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        Admin admin2 = new Admin();
        admin2.setPassword("password2");
        admin2.setUserid("23456");
        assertEquals("Admin created successfully!", adminService.saveAdmin(admin2));
        verify(adminRepository).save(Mockito.<Admin>any());
        verify(adminRepository).findById(Mockito.<String>any());
    }


    @Test
    void testLogin() throws NoDataFoundException {
        Admin admin = new Admin();
        admin.setPassword("password");
        admin.setUserid("12345");
        Optional<Admin> ofResult = Optional.of(admin);
        when(adminRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        Admin u = new Admin();
        u.setPassword("password");
        u.setUserid("12345");
        assertEquals("Login success", adminService.login(u));
        verify(adminRepository).findById(Mockito.<String>any());
    }


    @Test
    void testGetAccounts() {
        ArrayList<Account> accountList = new ArrayList<>();
        when(accountRepository.findAll()).thenReturn(accountList);
        List<Account> actualAccounts = adminService.getAccounts();
        assertSame(accountList, actualAccounts);
        assertTrue(actualAccounts.isEmpty());
        verify(accountRepository).findAll();
    }


}