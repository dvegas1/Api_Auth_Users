package com.microservices.components.enums;

import com.microservices.MicroservicesApplication;
import com.microservices.MicroservicesTestConfiguration;
import com.microservices.components.configurations.AppSettingsExternalConfigurations;
import com.microservices.components.configurations.AsyncConfig;
import com.microservices.components.interceptors.AppLoggerInterceptor;
import com.microservices.context.AppSessionContext;
import com.microservices.dtos.base.ApiBussinesResponse;
import com.microservices.dtos.base.BaseBusinessResponseDto;
import com.microservices.services.implementations.AuditService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.Arrays;
import java.util.Objects;


@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location=classpath:application-test.yml"})
@Import(MicroservicesTestConfiguration.class)
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class, loader = AnnotationConfigContextLoader.class, classes = { AppSessionContext.class, ApiBussinesResponse.class, AppLoggerInterceptor.class, MicroservicesApplication.class, AppSettingsExternalConfigurations.class, AuditService.class, AsyncConfig.class
})
class ResponseCodeTest {

    Jsonb jsonb = JsonbBuilder.create();
    ApiBussinesResponse businessResponse;

    @Autowired
    public ResponseCodeTest(ApiBussinesResponse businessResponse) {
        this.businessResponse = businessResponse;
    }

    @Test
    void getValue() {
        Assertions.assertEquals("000", ResponseCode.PROCESS_OK.getResponseCodeValue());
        Assertions.assertEquals("001", ResponseCode.HEADER_REQUIRED.getResponseCodeValue());
        Assertions.assertEquals("003", ResponseCode.PARAMETERS_REQUIRED.getResponseCodeValue());
        Assertions.assertEquals("052", ResponseCode.DATA_NOT_AVAILABLE.getResponseCodeValue());
        Assertions.assertEquals("999", ResponseCode.CUSTOM_ERROR_BUSINESS.getResponseCodeValue());
        Assertions.assertEquals("006", ResponseCode.ERROR_WRONG_PROGRAM_ID.getResponseCodeValue());
    }

    @Test
    void values() {
        Assertions.assertEquals(Arrays.asList(ResponseCode.PROCESS_OK, ResponseCode.HEADER_REQUIRED, ResponseCode.DATA_NOT_AVAILABLE, ResponseCode.PARAMETERS_REQUIRED, ResponseCode.CUSTOM_ERROR_BUSINESS, ResponseCode.ERROR_WRONG_PROGRAM_ID).size(), ResponseCode.values().length);
    }

    @Test
    void valueOf() {
        Assertions.assertEquals(ResponseCode.PROCESS_OK, ResponseCode.valueOf("PROCESS_OK"));
        Assertions.assertEquals(ResponseCode.HEADER_REQUIRED, ResponseCode.valueOf("HEADER_REQUIRED"));
        Assertions.assertEquals(ResponseCode.PARAMETERS_REQUIRED, ResponseCode.valueOf("PARAMETERS_REQUIRED"));
        Assertions.assertEquals(ResponseCode.DATA_NOT_AVAILABLE, ResponseCode.valueOf("DATA_NOT_AVAILABLE"));
        Assertions.assertEquals(ResponseCode.CUSTOM_ERROR_BUSINESS, ResponseCode.valueOf("CUSTOM_ERROR_BUSINESS"));
        Assertions.assertEquals(ResponseCode.ERROR_WRONG_PROGRAM_ID, ResponseCode.valueOf("ERROR_WRONG_PROGRAM_ID"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> ResponseCode.valueOf("Unknown"));
    }

    @Test
    void compareName() {
        Assertions.assertEquals("PROCESS_OK", ResponseCode.PROCESS_OK.name());
        Assertions.assertEquals("HEADER_REQUIRED", ResponseCode.HEADER_REQUIRED.name());
        Assertions.assertEquals("DATA_NOT_AVAILABLE", ResponseCode.DATA_NOT_AVAILABLE.name());
        Assertions.assertEquals("PARAMETERS_REQUIRED", ResponseCode.PARAMETERS_REQUIRED.name());
        Assertions.assertEquals("CUSTOM_ERROR_BUSINESS", ResponseCode.CUSTOM_ERROR_BUSINESS.name());
        Assertions.assertEquals("ERROR_WRONG_PROGRAM_ID", ResponseCode.ERROR_WRONG_PROGRAM_ID.name());


    }

    @Test
    void validateResponseCodeOnBusinessResponse() {
        Assertions.assertEquals("Data not available, please try again", jsonb.fromJson(Objects.requireNonNull(businessResponse.getResponse(ResponseCode.DATA_NOT_AVAILABLE.getResponseCodeValue()).getBody()).toString(), BaseBusinessResponseDto.class).getMessageResponse());
        Assertions.assertEquals("Invalid program ID", jsonb.fromJson(Objects.requireNonNull(businessResponse.getResponse(ResponseCode.ERROR_WRONG_PROGRAM_ID.getResponseCodeValue()).getBody()).toString(), BaseBusinessResponseDto.class).getMessageResponse());
    }


}
