package com.sports.exception;

import com.sports.common.ResponseCode;

public class PublicSiteException extends CustomException {
    public PublicSiteException(ResponseCode responseCode) {
        super(responseCode);
    }

    public PublicSiteException(ResponseCode responseCode, Object... args) {
        super(responseCode, args);
    }
}
