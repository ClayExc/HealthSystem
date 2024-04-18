package com.sports.exception;

import com.sports.common.ResponseCode;

import java.text.MessageFormat;

public class CustomException extends RuntimeException{

    //错误代码
    ResponseCode responseCode;

    public CustomException(ResponseCode responseCode){
        super(responseCode.getDesc());
        this.responseCode = responseCode;
    }

    public CustomException(ResponseCode responseCode, Object... args){
        super(responseCode.getDesc());
        String message = MessageFormat.format(responseCode.getDesc(), args);
        responseCode.setDesc(message);
        this.responseCode = responseCode;
    }

    public ResponseCode getResponseCode(){
        return responseCode;
    }

}