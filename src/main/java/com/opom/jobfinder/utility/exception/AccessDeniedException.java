package com.opom.jobfinder.utility.exception;

import com.opom.jobfinder.utility.MessageConstants;

public class AccessDeniedException extends CommonException{
    public AccessDeniedException(String message) {
        super(MessageConstants.ACCESS_DENIED_ERROR, message);
    }
}
