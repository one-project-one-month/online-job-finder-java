package com.opom.jobfinder.utility;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public record BaseResponse (
        @JsonFormat(shape = JsonFormat.Shape.STRING,  pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone="Asia/Yangon")
        Date timestamp,
        String errorCode,
        Object data,
        String message
) {
    public static BaseResponse of(String errorCode, Object data, String message) {
        return new BaseResponse(new Date(), errorCode, data, message);
    }
}