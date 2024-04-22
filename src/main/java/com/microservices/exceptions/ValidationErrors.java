package com.microservices.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationErrors extends Exception {
        public ValidationErrors(String message) {
            super(message);
        }
    }


