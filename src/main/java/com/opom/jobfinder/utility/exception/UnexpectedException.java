package com.opom.jobfinder.utility.exception;

import com.opom.jobfinder.utility.MessageConstants;

public class UnexpectedException extends CommonException{
    public UnexpectedException(String message) {
        super(MessageConstants.INTERNAL_SERVER_ERROR, message);
    }
}
