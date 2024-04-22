package com.microservices;

import com.microservices.components.configurations.MicroservicesConfigurer;
import com.microservices.components.interceptors.AppLoggerInterceptor;
import com.microservices.context.AppSessionContext;
import com.microservices.dtos.base.ApiResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.format.FormatterRegistry;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

/**
 * MicroservicesConfigurer
 * <p>
 * MicroservicesConfigurer class.
 * <p>
 * ESTE COMPONENTE FUE CONSTRUIDO SIGUIENDO LOS ESTANDARES DE DESARROLLO Y EL PROCEDIMIENTO
 * DE DESARROLLO DE APLICACIONES..
 *
 * @author Darwin Vegas.
 * @author darwinvegas1@gmail.com
 * @since 01/22/2020
 */
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class,
    loader = AnnotationConfigContextLoader.class,
    classes = {
        ApiResponse.class,
        AppLoggerInterceptor.class,
        AppSessionContext.class,
        MicroservicesConfigurer.class
    }
)
@Import(MicroservicesTestConfiguration.class)
@TestPropertySource(properties = {"spring.config.location=classpath:application-test.yml"})
class MicroservicesConfigurerTest {
    AppLoggerInterceptor appLoggerInterceptor;
    MicroservicesConfigurer microservicesConfigurer;
    InterceptorRegistry interceptorRegistry = new InterceptorRegistry();

    @Autowired
    public MicroservicesConfigurerTest(AppLoggerInterceptor appLoggerInterceptor,
                                       MicroservicesConfigurer microservicesConfigurer) {
        this.appLoggerInterceptor = appLoggerInterceptor;
        this.microservicesConfigurer = microservicesConfigurer;
    }

    @Test
    void testValidateConstructor() {
        Assertions.assertNotNull(appLoggerInterceptor);
        Assertions.assertNotNull(new MicroservicesConfigurer(null, null, null, null).appLoggerInterceptor(), "Error on execution configuration");
    }

    @Test
    void shouldValidBean() {
        Assertions.assertDoesNotThrow(() -> microservicesConfigurer.addInterceptors(interceptorRegistry));
    }

    @Test
    void givenExistsConverter_whenAddFormatters_thenSuccess() {
        Assertions.assertDoesNotThrow(() -> microservicesConfigurer.addFormatters(Mockito.mock(FormatterRegistry.class)));
        Assertions.assertThrows(NullPointerException.class, () -> microservicesConfigurer.addFormatters(null));
    }

}
