package com.ggtech.bankingapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.ggtech.bankingapp.model.Admin;
import com.ggtech.bankingapp.model.Customer;
import com.ggtech.bankingapp.model.LoginRequest;
import com.ggtech.bankingapp.model.LoginResponse;
import com.ggtech.bankingapp.util.JwtUtil;
import java.util.Arrays;
@Service
public class AuthenticationService {
	
	@Autowired
	private CustomerService custService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AdminService adminService;
	
	public LoginResponse register(Customer customer) throws Exception {
		List <SimpleGrantedAuthority> authorities;
		String role="ROLE_CUSTOMER";
		authorities=Arrays.stream(role.split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		String message= custService.saveCustomer(customer);
		// System.out.println(customer.getCustomerId());
		// System.out.println("Customer id"+Long.toString(customer.getCustomerId()));
		User user=new User(Long.toString(customer.getCustomerId()),customer.getPassword(),authorities);
		if(message.equals("Customer already exists!")) {
			throw new Exception("Customer already exists!");
		}else {
			 String jwtToken = jwtTokenUtil.generateToken(user);
			    return new LoginResponse(jwtToken);	
		}
	  }
	
	public LoginResponse authenticate(LoginRequest request) throws Exception {
		String message= custService.validateCustomer(request);
	    if(message.equals("Login success")) {
	    	authenticationManager.authenticate(
	    	        new UsernamePasswordAuthenticationToken(
	    	            request.getCustomerId(),
	    	            request.getPassword()
	    	        )
	    	    );
	    	    List <SimpleGrantedAuthority> authorities;
		        String role="ROLE_CUSTOMER";
	    	    authorities=Arrays.stream(role.split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	    		User user=new User(Long.toString(request.getCustomerId()),request.getPassword(),authorities);
	    	    var jwtToken = jwtTokenUtil.generateToken(user);
	    	    return new LoginResponse(jwtToken);
	    }else {
	    	throw new Exception("Login Falied");
	    }
	  }

	  public LoginResponse adminSave(Admin admin) throws Exception{
		List <SimpleGrantedAuthority> authorities;
		String role="ROLE_ADMIN";
		authorities=Arrays.stream(role.split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		String message= adminService.saveAdmin(admin);
		// System.out.println(customer.getCustomerId());
		// System.out.println("Customer id"+Long.toString(customer.getCustomerId()));
		User user=new User(admin.getUserid(),admin.getPassword(),authorities);
		if(message.equals("Admin already exists!")) {
			throw new Exception("Admin already exists!");
		}else {
			 String jwtToken = jwtTokenUtil.generateToken(user);
			    return new LoginResponse(jwtToken);	
		}
	  }

	  public LoginResponse adminLogin(Admin admin) throws Exception{

		String message= adminService.login(admin);
	    if(message.equals("Login success")) {
	    	authenticationManager.authenticate(
	    	        new UsernamePasswordAuthenticationToken(
	    	            admin.getUserid(),
	    	            admin.getPassword()
	    	        )
	    	    );
	    	    List <SimpleGrantedAuthority> authorities;
		        String role="ROLE_ADMIN";
	    	    authorities=Arrays.stream(role.split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	    		User user=new User(admin.getUserid(),admin.getPassword(),authorities);
	    	    var jwtToken = jwtTokenUtil.generateToken(user);
	    	    return new LoginResponse(jwtToken);
	    }else {
	    	throw new Exception("Login Falied");
	    }
	  }
}
