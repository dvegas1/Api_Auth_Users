package com.microservices.controllers.implementations;

import com.microservices.components.enums.ResponseCode;
import com.microservices.constants.ManagementGeneralConstants;
import com.microservices.controllers.contracts.AuthResponseController;
import com.microservices.dtos.base.ApiResponse;
import com.microservices.dtos.commons.StatusUser;
import com.microservices.dtos.messages.GenericMessagesBusinessResponse;
import com.microservices.dtos.messages.MessageBusinessResponse;
import com.microservices.dtos.requests.CreateUserRequest;
import com.microservices.dtos.responses.UserResponse;
import com.microservices.exceptions.ValidationErrors;
import com.microservices.services.contracts.IAuthService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * AuthResponseController
 * <p>
 * AuthResponseController class.
 * <p>
 * ESTE COMPONENTE FUE CONSTRUIDO SIGUIENDO LOS ESTANDARES DE DESARROLLO Y EL PROCEDIMIENTO
 * DE DESARROLLO DE APLICACIONES..
 *
 * @author Darwin Vegas.
 * @author darwinvegas1@gmail.com
 * @since 10/15/2021
 */
@RestController
@SuppressWarnings({ "java:S3457"})
public class ManagerInteractiveAuthResponseController implements AuthResponseController {
    private static final Logger LOGGER = LogManager.getLogger(ManagerInteractiveAuthResponseController.class);
    ApiResponse businessResponse;
    IAuthService authService;

    @Autowired
    public ManagerInteractiveAuthResponseController(ApiResponse businessResponse, IAuthService authService) {
        this.businessResponse = businessResponse;
        this.authService = authService;
    }

    @Override
    public ResponseEntity<Object> createUser(CreateUserRequest createUserRequest) {
        try {
            LOGGER.info("Start processing createUser");
            StatusUser rspCreateUser = authService.createUser(createUserRequest.getName().toUpperCase(), createUserRequest.getEmail(), createUserRequest.getPassword(), createUserRequest.getPhones());

            LOGGER.info("getResponse:{}",rspCreateUser);

            return businessResponse.getResponse(
                UserResponse.builder().status(
                    rspCreateUser
                ).build(),
                ResponseCode.PROCESS_OK.getResponseCodeValue()
            );
        } catch (ValidationErrors val) {
            LOGGER.error("006 Error ValidationErrors processing createUser ", val);
            return responseToValidation(val.getMessage());
        } catch (Exception e) {
            LOGGER.error(Arrays.asList(e.getStackTrace()));
            LOGGER.error("002 Error in createUser  ", e);
            return businessResponse.getResponse(
                UserResponse.builder().status(
                    StatusUser.builder().code(ManagementGeneralConstants.FALLA_GENERAL_SISTEMA_CODE).message(ManagementGeneralConstants.FALLA_GENERAL_SISTEMA_MENSAJE).build()
                ).build(),
                ResponseCode.BAD_REQUEST.getResponseCodeValue()
            );
        } finally {
            LOGGER.info("Finish processing createUser");
        }
    }

    public ResponseEntity<Object> responseToValidation(String msg) {
        return businessResponse.getResponse(
            GenericMessagesBusinessResponse.builder()
                .messageBusinessResponse(MessageBusinessResponse.builder()
                    .messagesBusiness(List.of(msg))
                    .build())
                .build(),
            ResponseCode.BAD_REQUEST.getResponseCodeValue()
        );
    }
}


