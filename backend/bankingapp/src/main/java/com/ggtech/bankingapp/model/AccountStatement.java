package com.ggtech.bankingapp.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class AccountStatement {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date endDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
