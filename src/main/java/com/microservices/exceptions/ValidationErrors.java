package com.microservices.exceptions;

import com.microservices.services.validations.ValidPhoneDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationErrors extends Exception {
        public ValidationErrors(String message) {
            super(message);
        }
    }


