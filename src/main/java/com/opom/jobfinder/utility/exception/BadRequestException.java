package com.opom.jobfinder.utility.exception;

import com.opom.jobfinder.utility.MessageConstants;

public class BadRequestException extends CommonException{
    public BadRequestException(String message) {
        super(MessageConstants.BAD_REQUEST_ERROR, message);
    }
}
