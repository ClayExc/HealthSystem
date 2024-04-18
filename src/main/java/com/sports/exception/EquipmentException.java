package com.sports.exception;

import com.sports.common.ResponseCode;

public class EquipmentException extends CustomException {
    public EquipmentException(ResponseCode responseCode) {
        super(responseCode);
    }

    public EquipmentException(ResponseCode responseCode, Object... args) {
        super(responseCode, args);
    }
}
