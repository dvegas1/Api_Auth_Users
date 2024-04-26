package com.microservices.controllers.implementations;


import com.microservices.MicroservicesTestConfiguration;
import com.microservices.components.configurations.AppSettingsExternalConfigurations;
import com.microservices.components.configurations.AsyncConfig;
import com.microservices.components.interceptors.AppLoggerInterceptor;
import com.microservices.constants.ManagementGeneralConstants;
import com.microservices.context.AppSessionContext;
import com.microservices.dtos.base.ApiBussinesResponse;
import com.microservices.dtos.base.BaseBusinessResponseDto;
import com.microservices.dtos.commons.DetailsUserDto;
import com.microservices.dtos.commons.StatusUser;
import com.microservices.dtos.requests.CreateUserRequest;
import com.microservices.dtos.requests.PhoneRequest;
import com.microservices.services.contracts.IAuthService;
import com.microservices.services.implementations.AuditService;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
@DataJpaTest
@AutoConfigureMockMvc
@Import(MicroservicesTestConfiguration.class)
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class, loader = AnnotationConfigContextLoader.class, classes = { AppSessionContext.class, ApiBussinesResponse.class, ManagerInteractiveAuthResponseController.class, AppSettingsExternalConfigurations.class,IAuthService.class,  AppLoggerInterceptor.class,AuditService.class, AsyncConfig.class})
@SuppressFBWarnings(value = {"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"}, justification = "Testing")
@TestPropertySource(properties = {"spring.config.location=classpath:application-test.yml"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ManagerInteractiveVoiceResponseControllerTest {
    private static final Logger LOGGER = LogManager.getLogger(ManagerInteractiveVoiceResponseControllerTest.class);
    private static ClientAndServer mockServer;
    private static int mockServerPort;
    AppSessionContext appSessionContext;
    AppSettingsExternalConfigurations appSettingsExternalConfigurations;
    @MockBean
    ApiBussinesResponse apiBussinesResponse;
    @MockBean
    AppSessionContext appSessionContextMock;
    Jsonb jsonb = JsonbBuilder.create();
    ManagerInteractiveAuthResponseController managerInteractiveAuthResponseController;
    @MockBean
    IAuthService iAuthService;
    @Mock
    IAuthService iAuthServiceMock;
    @Mock
    ApiBussinesResponse apiBussinesResponseMock;
    @InjectMocks
    ManagerInteractiveAuthResponseController managerInteractiveAuthResponseControllerMock;

    @Autowired
    public ManagerInteractiveVoiceResponseControllerTest(AppSessionContext appSessionContext,ApiBussinesResponse apiBussinesResponse,IAuthService iAuthService,ManagerInteractiveAuthResponseController managerInteractiveAuthResponseController,AppSettingsExternalConfigurations appSettingsExternalConfigurations) {
        this.iAuthService = iAuthService;
        this.apiBussinesResponse = apiBussinesResponse;
        this.managerInteractiveAuthResponseController = managerInteractiveAuthResponseController;
        this.appSessionContext = appSessionContext;
        this.appSettingsExternalConfigurations = appSettingsExternalConfigurations;

      //  this.managerInteractiveAuthResponseController =
      //  new ManagerInteractiveAuthResponseController(apiBussinesResponse,iAuthService);
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
        Assertions.assertNotNull(apiBussinesResponse);

        MockitoAnnotations.openMocks(this);
        Assertions.assertNotNull(appSessionContextMock);
    }

    @Test
    @DisplayName("SUCESS CREATE USER")
    void createUserSucess() throws Exception {
        HttpStatus httpStatus = HttpStatus.OK;
        StatusUser statusUser = StatusUser.builder().user(
            DetailsUserDto.builder()
                .id("4207db8af20e3333ee186f9a91d7ad27b8fffaf07b82abb9b2f6aed1079034d1")
                .created("2024-04-22T12:35:28.938923600")
                .modified("2024-04-22T12:35:28.938923600")
                .token("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkYXJ3aW52ZWdhczFAZ21haWwuY29tIiwiZXhwIjoxNzE0NjY3NzI5fQ.jPW8Z942Sl7p97Z90jSWVVQaA29zUF-PadCQSG_rxHSFaBPRW5vuHGPNashektbQvLkdE8-udj4pueqvDFaz1w")
                .isactive("TRUE")
                .last_login("2024-04-22T12:35:28.938923600").build()).code(ManagementGeneralConstants.USER_CREATE_SUCESS_CODE).message(ManagementGeneralConstants.USER_CREATE_SUCESS_MESSAGE).build();

        Mockito.when(iAuthServiceMock.createUser(Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),Mockito.any(ArrayList.class))).thenReturn(statusUser);

        httpStatus = HttpStatus.valueOf(httpStatus.value());
        String bodyResponse = jsonb.toJson(statusUser);

        Mockito.when(apiBussinesResponseMock.getResponse(Mockito.anyString())).thenReturn(new ResponseEntity<>(bodyResponse, httpStatus));

        Mockito.when(apiBussinesResponseMock.getResponse(Mockito.any(BaseBusinessResponseDto.class),Mockito.anyString())).thenReturn(new ResponseEntity<>(bodyResponse, httpStatus));

        ArrayList<PhoneRequest> phones = new ArrayList<>();
        phones.add(PhoneRequest.builder().number("123456").citycode("1").contrycode("12").build());

        Assertions.assertDoesNotThrow(()->this.managerInteractiveAuthResponseControllerMock.createUser(CreateUserRequest.builder().name("darwin").email("test@dddd.com").phones(phones).build()));
    }

    @Test
    @DisplayName("VALID EXEPTION CREATE USER")
    void createUserAssertDoest()  {

        ResponseEntity<Object> asd = this.managerInteractiveAuthResponseController.createUser(null);
        Assertions.assertNull(this.managerInteractiveAuthResponseController.createUser(null));
    }
}
