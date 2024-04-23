package com.microservices.services.implementations;

import com.microservices.MicroservicesTestConfiguration;
import com.microservices.components.configurations.AppSettingsExternalConfigurations;
import com.microservices.components.configurations.AsyncConfig;
import com.microservices.components.interceptors.AppLoggerInterceptor;
import com.microservices.constants.ManagementGeneralConstants;
import com.microservices.context.AppSessionContext;
import com.microservices.controllers.implementations.ManagerInteractiveAuthResponseController;
import com.microservices.dtos.base.ApiBussinesResponse;
import com.microservices.dtos.commons.StatusUser;
import com.microservices.dtos.requests.PhoneRequest;
import com.microservices.exceptions.InformationAuthException;
import com.microservices.repository.contracts.PhoneRepository;
import com.microservices.repository.contracts.UserRepository;
import com.microservices.repository.entities.UserEntity;
import com.microservices.services.contracts.IAuditService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockserver.configuration.ConfigurationProperties;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.socket.PortFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@DataJpaTest
@AutoConfigureMockMvc
@Import(MicroservicesTestConfiguration.class)
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class, loader = AnnotationConfigContextLoader.class, classes = {AppSessionContext.class, ApiBussinesResponse.class, ManagerInteractiveAuthResponseController.class, AppSettingsExternalConfigurations.class, AppLoggerInterceptor.class, AuditService.class, AsyncConfig.class, PhoneRepository.class, AuthService.class, UserRepository.class})
@TestPropertySource(properties = {"spring.config.location=classpath:application-test.yml"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AuthServiceMockTest {
    private static final Logger LOGGER = LogManager.getLogger(AuthServiceMockTest.class);

    @MockBean
    AppSessionContext appSessionContextMock;

    private static ClientAndServer mockServer;
    private static int mockServerPort;
    IAuditService auditService;
    PhoneRepository phoneRepository;
    AppSettingsExternalConfigurations appSettingsExternalConfigurations;

    @Autowired
    AuthService authService;

    @MockBean
    UserRepository userRepository;

    @Autowired
    public AuthServiceMockTest(
        UserRepository userRepository, PhoneRepository phoneRepository, IAuditService auditService, AppSettingsExternalConfigurations appSettingsExternalConfigurations) {
        this.auditService = auditService;
        this.phoneRepository = phoneRepository;
        this.appSettingsExternalConfigurations = appSettingsExternalConfigurations;
        this.userRepository = userRepository;
    }

    @BeforeAll
    static void beforeAll() {
        ConfigurationProperties.maxSocketTimeout(30000L);
        mockServerPort = PortFactory.findFreePort();
        mockServer = ClientAndServer.startClientAndServer(mockServerPort);

        while (!mockServer.hasStarted(5, 100L, TimeUnit.MILLISECONDS)) {
            LOGGER.warn("Wait started mock server");
        }
    }

    @AfterAll
    static void afterAll() {
        mockServer.stop();
        while (!mockServer.hasStopped(3, 100L, TimeUnit.MILLISECONDS)) {
            LOGGER.warn("Wait stop mock server");
        }
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        Assertions.assertNotNull(appSessionContextMock);
        MockitoAnnotations.openMocks(this);
        Assertions.assertNotNull(appSessionContextMock);
    }


    @Test
    @DisplayName("FAIL CREATE USER")
    void createUserFailNotCreate() throws InformationAuthException, ParseException {
        Mockito.when(userRepository.save(UserEntity.builder().name("darwin").email( "darwinvegas12313@gmail.com").password("12345").build())).thenReturn(null);

        ArrayList<PhoneRequest> listPohone = new ArrayList<>();
        listPohone.add(0, PhoneRequest.builder().contrycode("0").number("123456").citycode("1").build());

        StatusUser responseCreateUser = this.authService.createUser("darwin", "darwinvegas12313@gmail.com", "12345", listPohone);

        Assertions.assertEquals(ManagementGeneralConstants.USER_NOT_CREATE_MESSAGE, responseCreateUser.getMessage());
        Assertions.assertEquals(ManagementGeneralConstants.USER_NOT_CREATE_CODE, responseCreateUser.getCode());

    }

}