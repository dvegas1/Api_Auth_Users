package com.microservices.controllers;

import com.google.gson.Gson;
import com.microservices.MicroservicesTestConfiguration;
import com.microservices.context.AppSessionContext;
import com.microservices.controllers.implementations.PodInfoController;
import com.microservices.dtos.base.ApiResponse;
import com.microservices.dtos.messages.MessageResponseDto;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.Objects;

/**
 * Created on 21/04/2024
 *
 * @author dvegas
 * @since 21/04/2024
 */
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class,
    loader = AnnotationConfigContextLoader.class,
    classes = {
        PodInfoController.class,
        AppSessionContext.class,
        ApiResponse.class
    }
)
@Import(MicroservicesTestConfiguration.class)
@TestPropertySource(properties = {"spring.config.location=classpath:application-test.yml"})
@SuppressFBWarnings(value = {"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"}, justification = "Testing")
class PodInfoControllerTest {

    private static final Logger LOGGER = LogManager.getLogger(PodInfoControllerTest.class);

    @Autowired
    PodInfoController podInfoController;

    @Test
    void getPodInfo() {
        ResponseEntity<Object> responseEntity = podInfoController.getPodInfo();

        Assertions.assertNotNull(responseEntity);
    }

    @Test
    void sayWithError() {
        ResponseEntity<Object> responseEntity = podInfoController.getPodInfo();
        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCode().value());
        Assertions.assertNotNull(responseEntity.getBody());

        MessageResponseDto messageResponseDto = new Gson().fromJson(Objects.requireNonNull(responseEntity.getBody().toString()), MessageResponseDto.class);
        Assertions.assertEquals("200.1.000", messageResponseDto.getCode());
    }
}
