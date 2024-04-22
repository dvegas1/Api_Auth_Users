package com.microservices;

import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

/**
 * Created on 1/22/2021
 *
 * @author dvegas
 * @since 1/22/2021
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location=classpath:application-test.yml"})
@SuppressWarnings("java:S2699")

class MicroservicesApplicationTests {

    @Autowired
    OpenAPI openAPI;

    @Test
    void contextLoads() {
        Assertions.assertTrue(true);
    }

}