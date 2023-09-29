package com.ggtech.bankingapp.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ggtech.bankingapp.model.Admin;
import com.ggtech.bankingapp.model.Customer;
import com.ggtech.bankingapp.repository.AdminRepository;
import com.ggtech.bankingapp.repository.CustomerRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AdminRepository adminRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        Customer obj=customerRepository.findByCustomerId(Long.parseLong(username));
        System.out.println(obj);
        //System.out.println("Hi "+username);
        if(obj==null){
            Admin admin=adminRepository.findByUserid(username);
            if(admin==null){
                throw new UsernameNotFoundException("User not found", null);
            }else{
                List <SimpleGrantedAuthority> authorities;
		        String role="ROLE_ADMIN";
	    	    authorities=Arrays.stream(role.split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		        User user=new User(admin.getUserid(),admin.getPassword(),authorities);
                return user;
            }
        }else{
            List <SimpleGrantedAuthority> authorities;
		    String role="ROLE_CUSTOMER";
	    	authorities=Arrays.stream(role.split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		    System.out.println(authorities);
            User user=new User(Long.toString(obj.getCustomerId()),obj.getPassword(),authorities);
            return user;
        }
    }
    
}
