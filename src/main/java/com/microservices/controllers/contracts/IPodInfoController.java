package com.microservices.controllers.contracts;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


/**
 * IPodInfoController
 * <p>
 * IPodInfoController interface.
 * <p>
 * ESTE COMPONENTE FUE CONSTRUIDO SIGUIENDO LOS ESTANDARES DE DESARROLLO Y EL PROCEDIMIENTO DE DESARROLLO DE
 * APLICACIONES.
 *
 * @author Darwin Vegas.
 * @author darwinvegas1@gmail.coms
 * @since 04/21/2024
 */
public interface IPodInfoController {

    @Operation(summary = "Status microservices", description = "Get current status")
    @ApiImplicitParams({
        @ApiImplicitParam(name = HttpHeaders.ACCEPT, value = MediaType.APPLICATION_JSON_VALUE, required = true, paramType = "header", dataTypeClass = String.class, defaultValue = MediaType.APPLICATION_JSON_VALUE),
        @ApiImplicitParam(name = HttpHeaders.CONTENT_TYPE, value = MediaType.APPLICATION_JSON_VALUE, required = true, paramType = "header", dataTypeClass = String.class, defaultValue = MediaType.APPLICATION_JSON_VALUE)
    })
    ResponseEntity<Object> getPodInfo();
}
