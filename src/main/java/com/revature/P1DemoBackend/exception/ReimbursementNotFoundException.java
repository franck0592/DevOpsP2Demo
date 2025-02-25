package com.revature.P1DemoBackend.exception;

public class ReimbursementNotFoundException extends RuntimeException{
    public ReimbursementNotFoundException(String message){
        super(message);
    }
}
