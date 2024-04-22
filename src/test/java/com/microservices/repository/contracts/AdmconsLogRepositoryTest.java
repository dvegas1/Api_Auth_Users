package com.microservices.repository.contracts;

import com.microservices.MicroservicesApplication;
import com.microservices.MicroservicesTestConfiguration;
import com.microservices.components.configurations.AppSettingsExternalConfigurations;
import com.microservices.components.configurations.AsyncConfig;
import com.microservices.components.interceptors.AppLoggerInterceptor;
import com.microservices.context.AppSessionContext;
import com.microservices.dtos.base.ApiResponse;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@SuppressFBWarnings({"URF_UNREAD_FIELD"})
@RunWith(SpringRunner.class)
@DataJpaTest
@Import(MicroservicesTestConfiguration.class)
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class, loader = AnnotationConfigContextLoader.class, classes = { AppSessionContext.class, ApiResponse.class,AppLoggerInterceptor.class,  MicroservicesApplication.class,  AppSettingsExternalConfigurations.class,AsyncConfig.class})
@TestPropertySource(properties = {"spring.config.location=classpath:application-test.yml"})
@SuppressWarnings("squid:S5976")
@Sql(scripts = {"/database/admcons_log.sql",}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class AdmconsLogRepositoryTest {
    @MockBean
    AdmconsLogRepository admconsLogRepository;


    @Test
    @Sql(scripts = {"/database/admcons_log.sql",}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void findByIdLike() {
        var admconsLogRepositoryRsp = admconsLogRepository.findByIdLike("88","Test");
        Assertions.assertTrue(admconsLogRepositoryRsp.isEmpty());
    }
}