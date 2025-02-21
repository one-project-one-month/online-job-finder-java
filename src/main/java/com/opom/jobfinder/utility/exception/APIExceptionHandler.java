package com.opom.jobfinder.utility.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.opom.jobfinder.utility.BaseResponse;
import com.opom.jobfinder.utility.MessageConstants;
import com.opom.jobfinder.utility.Translator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import javax.crypto.BadPaddingException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class APIExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public BaseResponse businessException(BadRequestException ex) {
        log.error("Error ", ex);
        return BaseResponse.of(MessageConstants.BAD_REQUEST_ERROR, null, ex.getMessage());
    }

    @ExceptionHandler(UnexpectedException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse unexpectedException(UnexpectedException ex) {
        log.error("Error ", ex);
        return BaseResponse.of(MessageConstants.INTERNAL_SERVER_ERROR, null, ex.getMessage());
    }

    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            MissingServletRequestParameterException.class,
            MethodArgumentTypeMismatchException.class,
            HttpMessageNotReadableException.class,
            BadPaddingException.class,
            JsonProcessingException.class,
            HttpMessageNotReadableException.class,
            IndexOutOfBoundsException.class,
            NoResourceFoundException.class
    })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public BaseResponse validateException(Exception ex) {
        log.error("Error ", ex);
        if (ex instanceof MethodArgumentNotValidException) {
            BindingResult result = ((MethodArgumentNotValidException) ex).getBindingResult();
            Map<String, String> errList = new HashMap<>();
            for (FieldError fieldError : result.getFieldErrors()) {
                errList.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return BaseResponse.of(MessageConstants.BAD_REQUEST_ERROR, errList ,Translator.toLocale(MessageConstants.BAD_REQUEST_ERROR));
        }
        return BaseResponse.of(MessageConstants.BAD_REQUEST_ERROR, null ,Translator.toLocale(MessageConstants.BAD_REQUEST_ERROR));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse handleAllException(Exception ex) {
        log.error("Error ", ex);
        return BaseResponse.of(MessageConstants.INTERNAL_SERVER_ERROR, null, Translator.toLocale(MessageConstants.INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public BaseResponse handleUnauthorizedException(UnauthorizedException ex) {
        log.error("Error ", ex);
        return BaseResponse.of(MessageConstants.UNAUTHORIZED_ERROR, null, ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public BaseResponse handleAccessDeniedException(AccessDeniedException ex) {
        log.error("Error ", ex);
        return BaseResponse.of(MessageConstants.ACCESS_DENIED_ERROR, null, ex.getMessage());
    }

    @ExceptionHandler({
            BadCredentialsException.class,
            InternalAuthenticationServiceException.class
    })
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public BaseResponse handleCredentialException (Exception ex) {
        log.error("Error ", ex);
        return BaseResponse.of(
                MessageConstants.UNAUTHORIZED_ERROR,
                null,
                Translator.toLocale(MessageConstants.UNAUTHORIZED_ERROR)
        );
    }


}
