package com.ggtech.bankingapp.exceptions;

public class NoDataFoundException extends Exception {

    private String message;

    public NoDataFoundException(String message)
    {
        super(message);
    }
}
