package com.sports.exception;

import com.sports.common.ResponseCode;

public class FatigueDegreeException extends CustomException {
    public FatigueDegreeException(ResponseCode responseCode) {
        super(responseCode);
    }

    public FatigueDegreeException(ResponseCode responseCode, Object... args) {
        super(responseCode, args);
    }
}
