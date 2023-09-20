package com.ggtech.bankingapp.model;


import javax.persistence.Column;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequest {
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(nullable=false)
    private Long customerId;

    @Column(nullable=false)
    @Length(min=8, max=20, message="Password must be between 8 to 20 characters")
    private String password;
}
