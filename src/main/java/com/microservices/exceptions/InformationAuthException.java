package com.microservices.exceptions;


import com.microservices.components.enums.ResponseCode;
import lombok.Getter;

/**
 * InformationAuthException
 * <p>
 * InformationAuthException class.
 * <p>
 * ESTE COMPONENTE FUE CONSTRUIDO SIGUIENDO LOS ESTANDARES DE DESARROLLO Y EL PROCEDIMIENTO
 * DE DESARROLLO DE APLICACIONES..
 *
 * @author Darwin Vegas.
 * @author darwinvegas1@gmail.com
 * @since 9/10/2021
 */
@Getter
public class InformationAuthException extends Exception {
    private final ResponseCode responseCode;
    public InformationAuthException(String message) {
        super(message);
        this.responseCode = ResponseCode.CUSTOM_ERROR_BUSINESS;
    }

    public InformationAuthException(String message, ResponseCode responseCode) {
        super(message);
        this.responseCode = responseCode;
    }
}
