package com.sports.exception;

import com.sports.common.ResponseCode;

public class StandardRecipeException extends CustomException  {
    public StandardRecipeException(ResponseCode responseCode) {
        super(responseCode);
    }

    public StandardRecipeException(ResponseCode responseCode, Object... args) {
        super(responseCode, args);
    }
}
