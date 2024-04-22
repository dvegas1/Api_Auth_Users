package com.microservices.components.validations;

import com.microservices.MicroservicesTestConfiguration;
import com.microservices.context.AppSessionContext;
import com.microservices.dtos.base.ApiResponse;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.web.context.request.WebRequest;

import java.util.Objects;

@SuppressFBWarnings("UWF_UNWRITTEN_FIELD")
@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class,
    loader = AnnotationConfigContextLoader.class,
    classes = {
        AppSessionContext.class,
        ApiResponse.class
    }
)
@Import(MicroservicesTestConfiguration.class)
@TestPropertySource(properties = {"spring.config.location=classpath:application-test.yml"})
class AppGlobalExceptionHandlerTest {

    @Autowired
    ApiResponse businessResponse;

    WebRequest webRequest;

    @Test
    void supportResponseHandleConflict() {

        AppGlobalExceptionHandler handler = new AppGlobalExceptionHandler(businessResponse) {
            private String getRequestUri(WebRequest request) {
                return "http://localhost.me";
            }
        };

        final ResponseEntity<Object> handled = handler.handleConflict(new RuntimeException("Fake"));
        Assertions.assertTrue(Objects.requireNonNull(handled.getBody()).toString().contains("\"message\":\"Params required\",\"data\":{\"messagesBusiness\":[\"Fake\"]}"));
    }

    @Test
    void supportResponseHandleAllExceptions() {

        AppGlobalExceptionHandler handler = new AppGlobalExceptionHandler(businessResponse) {
            private String getRequestUri(WebRequest request) {
                return "http://localhost.me";
            }
        };

        final ResponseEntity<Object> handled = handler.handleAllExceptions(new Exception("Fake"), null);
        Assertions.assertTrue(Objects.requireNonNull(handled.getBody()).toString().contains("\"message\":\"Params required\",\"data\":{\"messagesBusiness\":[\"Fake\"]}"));
    }


}
