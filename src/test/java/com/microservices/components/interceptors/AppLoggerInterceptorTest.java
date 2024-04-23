package com.microservices.components.interceptors;

import com.microservices.MicroservicesApplication;
import com.microservices.MicroservicesTestConfiguration;
import com.microservices.components.configurations.AppSettingsExternalConfigurations;
import com.microservices.constants.InterceptorConstants;
import com.microservices.constants.LoggerConstants;
import com.microservices.context.AppSessionContext;
import com.microservices.dtos.base.ApiBussinesResponse;
import org.apache.http.HttpHeaders;
import org.apache.logging.log4j.ThreadContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@SpringBootTest
@AutoConfigureMockMvc
@Import({MicroservicesTestConfiguration.class, PersistenceExceptionTranslationAutoConfiguration.class})
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class, loader = AnnotationConfigContextLoader.class, classes = { AppSessionContext.class, ApiBussinesResponse.class, AppLoggerInterceptor.class, MicroservicesApplication.class, AppSettingsExternalConfigurations.class})
@TestPropertySource(properties = {"spring.config.location=classpath:application-test.yml"})
class AppLoggerInterceptorTest {


    @Autowired
    private AppLoggerInterceptor handlerAdapter;

    @Autowired
    private AppSessionContext appSessionContext;


    @Test
    void preHandleTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/test");
        request.addHeader(InterceptorConstants.HEADER_USER_ID, "joe");
        request.addHeader(InterceptorConstants.HEADER_REQUEST_ID, "TRX001");
        request.addHeader(InterceptorConstants.HEADER_TENANT_ID, "ABC123");
        request.addHeader(HttpHeaders.AUTHORIZATION, "Bearer 3da2eab1dc7a3597d0bef899f53804a0");
        request.setMethod("GET");

        MockHttpServletResponse response = new MockHttpServletResponse();


        handlerAdapter.preHandle(request, response, "");

        Assertions.assertNotNull(appSessionContext.getApplicationSession());
        Assertions.assertNotNull(ThreadContext.get(LoggerConstants.KEY_USER_ID));
        Assertions.assertNotNull(ThreadContext.get(LoggerConstants.KEY_REQUEST_ID));
        Assertions.assertNotNull(ThreadContext.get(LoggerConstants.KEY_TENANT_ID));

        Assertions.assertEquals("joe", ThreadContext.get(LoggerConstants.KEY_USER_ID));
        Assertions.assertEquals("TRX001", ThreadContext.get(LoggerConstants.KEY_REQUEST_ID));
        Assertions.assertEquals("ABC123", ThreadContext.get(LoggerConstants.KEY_TENANT_ID));

        Assertions.assertEquals("joe", appSessionContext.getApplicationSession().getUserId());
        Assertions.assertEquals("TRX001", appSessionContext.getApplicationSession().getRequestId());
        Assertions.assertEquals("ABC123", appSessionContext.getApplicationSession().getTenantId());
    }


}
