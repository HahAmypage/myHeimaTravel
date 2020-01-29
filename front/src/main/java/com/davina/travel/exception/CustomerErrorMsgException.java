package com.davina.travel.exception;

public class CustomerErrorMsgException extends RuntimeException {

    public CustomerErrorMsgException(){

    }
    public CustomerErrorMsgException(String msg){
        super(msg);
    }
}
