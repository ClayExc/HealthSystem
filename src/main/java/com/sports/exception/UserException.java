package com.sports.exception;

import com.sports.common.ResponseCode;

public class UserException extends CustomException {


    public UserException(ResponseCode responseCode) {
        super(responseCode);
    }

    public UserException(ResponseCode responseCode, Object... args) {
        super(responseCode, args);
    }

}