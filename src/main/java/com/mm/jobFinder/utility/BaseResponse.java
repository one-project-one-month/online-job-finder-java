package com.mm.jobFinder.utility;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING,  pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone="Asia/Yangon")
    private LocalDateTime timestamp = LocalDateTime.now();
    private String errorCode;
    private Object data;
    private String message;

    public BaseResponse(String errorCode, Object data, String message) {
        this.errorCode = errorCode;
        this.data = data;
        this.message = message;
    }
}
