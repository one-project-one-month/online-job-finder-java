package com.opom.jobfinder.utility;

public abstract class BaseService {
    protected BaseResponse successResponse (Object object) {
        return BaseResponse.of(MessageConstants.SUCCESS, object, Translator.toLocale(MessageConstants.SUCCESS));
    }

    protected BaseResponse errorResponse(String errorCode, String message) {
        return new BaseResponse(errorCode, null, Translator.toLocale(message));
    }

}