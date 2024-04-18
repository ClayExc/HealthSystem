package com.sports.exception;

import com.sports.common.ResponseCode;

public class DiseaseException extends CustomException {
    public DiseaseException(ResponseCode responseCode) {
        super(responseCode);
    }

    public DiseaseException(ResponseCode responseCode, Object... args) {
        super(responseCode, args);
    }
}
