package com.microservices.exceptions;

public class CryptographicException extends RuntimeException {
    public CryptographicException(String message) {
        super(message);
    }
}
