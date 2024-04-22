package com.microservices.dtos.requests;


import com.microservices.repository.dto.UserPhonesDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
 * @since 9/8/2021
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
    @NotEmpty(message = "The name is not valid, it must contain only alphanumeric characters and its length must be between 3 and 30 characters. Example: Juan Rodriguez")
    @Pattern(regexp = "^[a-zA-ZáéíóúüÁÉÍÓÚÜñÑ\\s]{3,20}(['-][a-zA-ZáéíóúüÁÉÍÓÚÜñÑ\\s]+)*$", message = "The name is not valid, it must contain only alphanumeric characters and its length must be between 3 and 30 characters. Example: Juan Rodriguez")
    String name;


    @ApiModelProperty(name = "email", value = "email", example = "juan@rodriguez.org")
    @Length(min = 11,max = 100,message = "The length of the Name field must be between 11 and 100 characters maximum")
    @Pattern(regexp = "^(?!-)[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+(?<!-)\\@([a-zA-Z]+(\\.[a-zA-Z]+))+$", message = "Invalid the field must contain a correct format. Example: user@domini.com")
    @NotEmpty(message = "Invalid the field must contain a correct format. Example: user@domini.com")
    String email;


    @ApiModelProperty(name = "password", value = "password", example = "Darwin.123+")
    @NotEmpty(message = "Invalid the field must contain a correct format and meet the following criteria: " +
        "At least one uppercase or lowercase letter, " +
        "At least one digit number " +
        "At least one special character " +
        "The minimum length is 8 characters " +
        "Example: MyStr0ngP@$$w0rd")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Invalid the field must contain a correct format and meet the following criteria: " +
        "At least one uppercase or lowercase letter, " +
        "At least one numeric digit " +
        "At least one special character " +
        "The minimum length is 8 characters " +
        "Example: MyStr0ngP@$$w0rd")
    String password;

    @ApiModelProperty(name = "phones", value = "Document Number for client.", example = "87654321")
    ArrayList<UserPhonesDTO> phones;
}