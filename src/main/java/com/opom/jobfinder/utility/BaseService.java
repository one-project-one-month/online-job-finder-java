package com.opom.jobfinder.utility;

import com.opom.jobfinder.utility.BaseResponse;
import com.opom.jobfinder.utility.MessageConstants;
import com.opom.jobfinder.utility.Translator;

public abstract class BaseService {
    protected BaseResponse successResponse (Object object) {
        return new BaseResponse(MessageConstants.SUCCESS, object, Translator.toLocale(MessageConstants.SUCCESS));
    }
}