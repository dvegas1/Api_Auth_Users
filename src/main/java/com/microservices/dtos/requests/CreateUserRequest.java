package com.microservices.dtos.requests;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * BalanceInquiryRequest
 * <p>
 * BalanceInquiryRequest class.
 * <p>
 * ESTE COMPONENTE FUE CONSTRUIDO SIGUIENDO LOS ESTANDARES DE DESARROLLO Y EL PROCEDIMIENTO
 * DE DESARROLLO DE APLICACIONES..
 *
 * @author Darwin Vegas.
 * @author darwinvegas1@gmail.com
 * @since 21/04/2024
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(description = "Request for balance inquiry")
@SuppressWarnings({"java:S2221", "java:S1192", "java:S2133", "java:S2629", "DuplicatedCode"})
public class CreateUserRequest implements Serializable {
    @ApiModelProperty(name = "name", value = "Full name", example = "Juan Rodriguez")
    @NotBlank(message = "The name field cannot be empty")
    @Pattern(regexp = "^[a-zA-ZáéíóúüÁÉÍÓÚÜñÑ\\s]{3,20}(['-][a-zA-ZáéíóúüÁÉÍÓÚÜñÑ\\s]+)*$", message = "The name is not valid, it must contain only alphanumeric characters and its length must be between 3 and 30 characters. Example: Juan Rodriguez")
    String name;

    @ApiModelProperty(name = "email", value = "email", example = "juan@rodriguez.org")
    @Length(min = 11,max = 100,message = "The length of the Name field must be between 11 and 100 characters maximum")
    @Pattern(regexp = "^(?!-)[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+(?<!-)\\@([a-zA-Z]+(\\.[a-zA-Z]+))+$", message = "Invalid the field must contain a correct format. Example: user@domini.com")
    @NotBlank(message = "The email field cannot be empty")
    String email;


    @ApiModelProperty(name = "password", value = "password", example = "Darwin.123+")
    @NotBlank(message = "The password field cannot be empty")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Invalid the field must contain a correct format and meet the following criteria: " +
        "At least one uppercase or lowercase letter, " +
        "At least one numeric digit " +
        "At least one special character " +
        "The minimum length is 8 characters " +
        "Example: MyStr0ngP@$$w0rd")
    String password;

    @ApiModelProperty(name = "phones", value = "Phones to client.", example = "\"phones\":[{\"number\":\"1234567\",\"citycode\":\"1\",\"contrycode\":\"57\"},{\"number\":\"4242727712\",\"citycode\":\"2\",\"contrycode\":\"58\"},{\"number\":\"987654321\",\"citycode\":\"3\",\"contrycode\":\"51\"}]")
    @Valid
    ArrayList<PhoneRequest> phones;
}
