package com.microservices.components.validations;


import com.microservices.components.enums.ResponseCode;
import com.microservices.dtos.base.ApiResponse;
import com.microservices.dtos.messages.GenericMessagesBusinessResponse;
import com.microservices.dtos.messages.MessageBusinessResponse;
import com.microservices.exceptions.ValidationErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@SuppressWarnings({"java:S3242", "CanBeFinal"})
public class AppGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    ApiResponse businessResponse;

    @Autowired
    public AppGlobalExceptionHandler(ApiResponse businessResponse) {
        this.businessResponse = businessResponse;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> handleConflict(RuntimeException ex) {
        return businessResponse.getResponse(
            GenericMessagesBusinessResponse.builder()
                .messageBusinessResponse(MessageBusinessResponse.builder()
                    .messagesBusiness(List.of(ex.getLocalizedMessage()))
                    .build())
                .build(),
            ResponseCode.BAD_REQUEST.getResponseCodeValue()
        );
    }

    @ExceptionHandler(Exception.class)
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        return businessResponse.getResponse(
            GenericMessagesBusinessResponse.builder()
                .messageBusinessResponse(MessageBusinessResponse.builder()
                    .messagesBusiness(List.of(ex.getMessage()))
                    .build())
                .build(),
            ResponseCode.BAD_REQUEST.getResponseCodeValue()
        );
    }
    @Override
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return businessResponse.getResponse(
            GenericMessagesBusinessResponse.builder()
                .messageBusinessResponse(MessageBusinessResponse.builder()
                    .messagesBusiness(ex.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(fieldError -> String.format("Field %s: %s", fieldError.getField(), fieldError.getDefaultMessage()))
                        .collect(Collectors.toList()))
                    .build())
                .build(),
            ResponseCode.BAD_REQUEST.getResponseCodeValue()
        );
    }
    @ExceptionHandler(ValidationErrors.class)
    public ResponseEntity<Object> handleNotFoundException(ValidationErrors ex) {
        return businessResponse.getResponse(
            GenericMessagesBusinessResponse.builder()
                .messageBusinessResponse(MessageBusinessResponse.builder()
                    .messagesBusiness(List.of(ex.getMessage()))
                    .build())
                .build(),
            ResponseCode.BAD_REQUEST.getResponseCodeValue()
        );
    }


}
