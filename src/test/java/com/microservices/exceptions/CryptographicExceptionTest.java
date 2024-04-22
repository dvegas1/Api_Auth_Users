package com.microservices.exceptions;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CryptographicExceptionTest {

    @Test
    void validateConstructor() {
        CryptographicException cryptographicException = Assertions.assertThrows(CryptographicException.class, this::fakeMethod);
        Assertions.assertEquals("hello error", cryptographicException.getMessage());
    }

    void fakeMethod() throws CryptographicException {
        throw new CryptographicException("hello error");
    }
}
