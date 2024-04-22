package com.microservices.dtos.commons;

import com.microservices.repository.dto.UserPhonesDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

/**
 * ApplicationSession
 * <p>
 * ApplicationSession class.
 * <p>
 * ESTE COMPONENTE FUE CONSTRUIDO SIGUIENDO LOS ESTANDARES DE DESARROLLO Y EL PROCEDIMIENTO
 * DE DESARROLLO DE APLICACIONES..
 *
 * @author Darwin Vegas.
 * @author darwinvegas1@gmail.com
 * @since 04/21/2024
 */
@Data
@AllArgsConstructor
@Builder
public class DetailsUserDto {
    String id;
    String created;
    String modified;
    String last_login;
    String token;
    String isactive;
    ArrayList<UserPhonesDTO> phones;
}
