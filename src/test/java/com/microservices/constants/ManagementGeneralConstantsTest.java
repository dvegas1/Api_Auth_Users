package com.microservices.constants;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;


@SuppressWarnings({"java:S5961"})
class ManagementGeneralConstantsTest {

    @Test
    void validateConstructor() {
        Assertions.assertThrows(InvocationTargetException.class, () -> {
            Constructor<ManagementGeneralConstants> constructor = ManagementGeneralConstants.class.getDeclaredConstructor();
            Assertions.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
            constructor.setAccessible(true);
            constructor.newInstance();
        });
    }

    @Test
    void getValue() {
        Assertions.assertEquals("We are unable to accommodate your request at this time, please try again later", ManagementGeneralConstants.FALLA_GENERAL_SISTEMA_MENSAJE);
        Assertions.assertEquals("500", ManagementGeneralConstants.FALLA_GENERAL_SISTEMA_CODE);
    }
}
