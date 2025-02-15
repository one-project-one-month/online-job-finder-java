package com.opom.jobfinder.utility;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record BaseResponse (
        @JsonFormat(shape = JsonFormat.Shape.STRING,  pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone="Asia/Yangon")
        LocalDateTime timestamp,
        String errorCode,
        Object data,
        String message
) {
    public static BaseResponse of(String errorCode, Object data, String message) {
        return new BaseResponse(LocalDateTime.now(), errorCode, data, message);
    }
}