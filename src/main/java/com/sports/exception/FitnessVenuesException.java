package com.sports.exception;

import com.sports.common.ResponseCode;

public class FitnessVenuesException extends CustomException {

    public FitnessVenuesException(ResponseCode responseCode) {
        super(responseCode);
    }

    public FitnessVenuesException(ResponseCode responseCode, Object... args) {
        super(responseCode, args);
    }
}
