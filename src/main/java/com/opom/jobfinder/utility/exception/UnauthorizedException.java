package com.opom.jobfinder.utility.exception;

import com.opom.jobfinder.utility.MessageConstants;

public class UnauthorizedException extends CommonException{
    public UnauthorizedException(String message) {
        super(MessageConstants.UNAUTHORIZED_ERROR, message);
    }
}
