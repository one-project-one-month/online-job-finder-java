package com.opom.jobfinder.utility;

public abstract class BaseService {
    protected BaseResponse successResponse (Object object) {
        return BaseResponse.of(MessageConstants.SUCCESS, object, Translator.toLocale(MessageConstants.SUCCESS));
    }
}