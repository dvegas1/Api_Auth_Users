package com.microservices.controllers.contracts;

import com.microservices.constants.CategoryRestConstants;
import com.microservices.dtos.base.BaseBusinessResponseDto;
import com.microservices.dtos.requests.CreateUserRequest;
import com.microservices.dtos.responses.UserResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * AuthResponseController
 * <p>
 * AuthResponseController  class.
 * <p>
 * ESTE COMPONENTE FUE CONSTRUIDO SIGUIENDO LOS ESTANDARES DE DESARROLLO Y EL PROCEDIMIENTO
 * DE DESARROLLO DE APLICACIONES..
 *
 * @author Darwin Vegas.
 * @author darwinvegas1@gmail.com
 * @since 10/04/2021
 */

@Api(value = "/", tags = {CategoryRestConstants.CATEGORY_INTERACTIVE_RESPONSE_MANAGEMENT}, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public interface AuthResponseController {
    @PostMapping(path = "/auth/createUser")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserResponse.class))),
        @ApiResponse(responseCode = "400", description = "Custom business error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BaseBusinessResponseDto.class))),
    })
    @ApiImplicitParams({
        @ApiImplicitParam(name = HttpHeaders.ACCEPT, value = MediaType.APPLICATION_JSON_VALUE, required = true, paramType = "header", dataTypeClass = String.class, defaultValue = MediaType.APPLICATION_JSON_VALUE),
        @ApiImplicitParam(name = HttpHeaders.CONTENT_TYPE, value = MediaType.APPLICATION_JSON_VALUE, required = true, paramType = "header", dataTypeClass = String.class, defaultValue = MediaType.APPLICATION_JSON_VALUE),
    })
    ResponseEntity<Object> createUser(@Valid @NotNull @RequestBody CreateUserRequest createUserRequest);
}
