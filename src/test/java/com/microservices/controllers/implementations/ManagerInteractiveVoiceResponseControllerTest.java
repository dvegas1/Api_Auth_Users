package com.microservices.controllers.implementations;


import com.microservices.MicroservicesTestConfiguration;
import com.microservices.components.configurations.AppSettingsExternalConfigurations;
import com.microservices.components.configurations.AsyncConfig;
import com.microservices.components.interceptors.AppLoggerInterceptor;
import com.microservices.context.AppSessionContext;
import com.microservices.dtos.base.ApiResponse;
import com.microservices.dtos.requests.CreateUserRequest;
import com.microservices.repository.dto.UserPhonesDTO;
import com.microservices.services.implementations.AuditService;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockserver.configuration.ConfigurationProperties;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.socket.PortFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import com.microservices.services.contracts.IAuthService;
@DataJpaTest
@AutoConfigureMockMvc
@Import(MicroservicesTestConfiguration.class)
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class, loader = AnnotationConfigContextLoader.class, classes = { AppSessionContext.class, ApiResponse.class, ManagerInteractiveAuthResponseController.class, AppSettingsExternalConfigurations.class,IAuthService.class,  AppLoggerInterceptor.class,  AppSessionContext.class,AuditService.class, AsyncConfig.class,ApiResponse.class})
@SuppressFBWarnings(value = {"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"}, justification = "Testing")
@TestPropertySource(properties = {"spring.config.location=classpath:application-test.yml"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ManagerInteractiveVoiceResponseControllerTest {


    private static final Logger LOGGER = LogManager.getLogger(ManagerInteractiveVoiceResponseControllerTest.class);
    private static ClientAndServer mockServer;
    private static int mockServerPort;

    @Mock
    ApiResponse ApiResponse;

    @Mock
    AppSessionContext appSessionContextMock;
    Jsonb jsonb = JsonbBuilder.create();
    AppSettingsExternalConfigurations AppSettingsExternalConfigurations;

    ManagerInteractiveAuthResponseController managerInteractiveAuthResponseController;

    @InjectMocks
    ManagerInteractiveAuthResponseController managerInteractiveAuthResponseControllerInje;

    @Mock
    ApiResponse businessResponseMock;


    @InjectMocks
    IAuthService IAuthService;
    @Mock
    ApiResponse businessResponse;

    @Autowired
    public ManagerInteractiveVoiceResponseControllerTest(IAuthService IAuthService,ApiResponse businessResponse) {
        this.IAuthService = IAuthService;
        this.businessResponse = businessResponse;
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
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Assertions.assertNotNull(appSessionContextMock);
        Assertions.assertNotNull(ApiResponse);

        MockitoAnnotations.openMocks(this);
        Assertions.assertNotNull(appSessionContextMock);
        Assertions.assertNotNull(businessResponseMock);
    }

    @Test
    void createUserSucess() {
        ArrayList<UserPhonesDTO> phones = new ArrayList<>();
        phones.add(UserPhonesDTO.builder().number("123456").citycode("1").contrycode("12").build());
        managerInteractiveAuthResponseControllerInje.createUser(CreateUserRequest.builder().name("darwin").email("test@dddd.com").phones(phones).build());
    }
}
