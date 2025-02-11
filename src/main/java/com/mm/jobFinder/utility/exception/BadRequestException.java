package com.mm.jobFinder.utility.exception;

import com.mm.jobFinder.utility.MessageConstants;

public class BadRequestException extends CommonException{
    public BadRequestException(String message) {
        super(MessageConstants.BAD_REQUEST_ERROR, message);
    }
}
