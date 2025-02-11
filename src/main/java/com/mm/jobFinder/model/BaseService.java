package com.mm.jobFinder.model;

import com.mm.jobFinder.utility.BaseResponse;
import com.mm.jobFinder.utility.MessageConstants;
import com.mm.jobFinder.utility.Translator;

public abstract class BaseService {
    protected BaseResponse successResponse (Object object) {
        return new BaseResponse(MessageConstants.SUCCESS, object, Translator.toLocale(MessageConstants.SUCCESS));
    }
}
