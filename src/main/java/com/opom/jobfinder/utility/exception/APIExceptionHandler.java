package com.opom.jobfinder.utility.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.opom.jobfinder.utility.BaseResponse;
import com.opom.jobfinder.utility.MessageConstants;
import com.opom.jobfinder.utility.Translator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.crypto.BadPaddingException;

@RestControllerAdvice
@Slf4j
public class APIExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public BaseResponse businessException(BadRequestException ex) {
        log.error("Error ", ex);
        return new BaseResponse(MessageConstants.BAD_REQUEST_ERROR, null, ex.getMessage());
    }

    @ExceptionHandler(UnexpectedException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse unexpectedException(BadRequestException ex) {
        log.error("Error ", ex);
        return new BaseResponse(MessageConstants.INTERNAL_SERVER_ERROR, null, ex.getMessage());
    }

    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            MissingServletRequestParameterException.class,
            MethodArgumentTypeMismatchException.class,
            HttpMessageNotReadableException.class,
            BadPaddingException.class,
            JsonProcessingException.class,
            HttpMessageNotReadableException.class,
            IndexOutOfBoundsException.class
    })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public BaseResponse validateException(Exception ex) {
        log.error("Error ", ex);
        return new BaseResponse(MessageConstants.BAD_REQUEST_ERROR, null ,Translator.toLocale(MessageConstants.BAD_REQUEST_ERROR));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse handleAllException(Exception ex) {
        log.error("Error ", ex);
        return new BaseResponse(MessageConstants.INTERNAL_SERVER_ERROR, null ,Translator.toLocale(MessageConstants.INTERNAL_SERVER_ERROR));
    }
}
