package com.microservices.exceptions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ValidationErrorsTest {

    @Test
    void validateConstructor() {
        ValidationErrors validationErrors = Assertions.assertThrows(ValidationErrors.class, this::fakeMethod);
        Assertions.assertEquals("hello error", validationErrors.getMessage());
    }

    void fakeMethod() throws ValidationErrors {
        throw new ValidationErrors("hello error");
    }
}
