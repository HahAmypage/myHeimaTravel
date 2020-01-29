package com.davina.admin.exception;

/**
 * 自定义异常
 */
public class CustomerErrorMsgException extends Exception {
    public CustomerErrorMsgException(){}
    public CustomerErrorMsgException(String errorMsg){
        super(errorMsg);//将异常消息给到父类，以后可以通过父类的成员方法获取
    }
}
