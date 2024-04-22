package com.microservices.constants;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

class LoggerConstantsTest {

    @Test
    void validateConstructor() {
        Assertions.assertThrows(InvocationTargetException.class, () -> {
            Constructor<LoggerConstants> constructor = LoggerConstants.class.getDeclaredConstructor();
            Assertions.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
            constructor.setAccessible(true);
            constructor.newInstance();
        });
    }


}
