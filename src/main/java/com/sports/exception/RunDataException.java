package com.sports.exception;

import com.sports.common.ResponseCode;

public class RunDataException extends CustomException {
    public RunDataException(ResponseCode responseCode) {
        super(responseCode);
    }

    public RunDataException(ResponseCode responseCode, Object... args) {
        super(responseCode, args);
    }
}
