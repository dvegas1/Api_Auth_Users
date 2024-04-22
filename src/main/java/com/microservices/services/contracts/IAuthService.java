package com.microservices.services.contracts;

import com.microservices.dtos.commons.StatusUser;
import com.microservices.dtos.requests.PhoneRequest;
import com.microservices.exceptions.InformationAuthException;
import com.microservices.exceptions.ValidationErrors;
import com.microservices.repository.dto.UserPhonesDTO;

import java.text.ParseException;
import java.util.ArrayList;


/**
 * AuthService
 * <p>
 * AuthService class.
 * <p>
 * ESTE COMPONENTE FUE CONSTRUIDO SIGUIENDO LOS ESTANDARES DE DESARROLLO Y EL PROCEDIMIENTO
 * DE DESARROLLO DE APLICACIONES.
 *
 * @author Darwin Vegas.
 * @author darwinvegas1@gmail.com
 * @since 20/04/2024
 */
public interface IAuthService {
    StatusUser createUser(String name, String email, String password, ArrayList<PhoneRequest> phones) throws InformationAuthException, ParseException, ValidationErrors;
}
