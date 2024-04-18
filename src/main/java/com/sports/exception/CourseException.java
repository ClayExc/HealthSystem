package com.sports.exception;

import com.sports.common.ResponseCode;

public class CourseException extends CustomException  {

    public CourseException(ResponseCode responseCode) {
        super(responseCode);
    }

    public CourseException(ResponseCode responseCode, Object... args) {
        super(responseCode, args);
    }
}
