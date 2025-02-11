package com.mm.jobFinder.utility.exception;

import com.mm.jobFinder.utility.MessageConstants;

public class UnexpectedException extends CommonException{
    public UnexpectedException(String message) {
        super(MessageConstants.INTERNAL_SERVER_ERROR, message);
    }
}
