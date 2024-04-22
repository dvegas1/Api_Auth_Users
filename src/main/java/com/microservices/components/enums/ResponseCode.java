package com.microservices.components.enums;

/**
 * ResponseCode
 * <p>
 * ResponseCode class.
 * <p>
 * ESTE COMPONENTE FUE CONSTRUIDO SIGUIENDO LOS ESTANDARES DE DESARROLLO Y EL PROCEDIMIENTO
 * DE DESARROLLO DE APLICACIONES..
 *
 * @author Darwin Vegas.
 * @author darwinvegas1@gmail.com
 * @since 04/21/2024
 */
public enum ResponseCode {
    PROCESS_OK("200"),
    BAD_REQUEST("400"),
    HEADER_REQUIRED("001"),
    PARAMETERS_REQUIRED("003"),
    DATA_NOT_AVAILABLE("052"),
    CUSTOM_ERROR_BUSINESS("999"),
    ERROR_WRONG_PROGRAM_ID("006");


    private final String responseCodeValue;

    ResponseCode(String responseCodeValue) {
        this.responseCodeValue = responseCodeValue;
    }

    public String getResponseCodeValue() {
        return responseCodeValue;
    }

}
