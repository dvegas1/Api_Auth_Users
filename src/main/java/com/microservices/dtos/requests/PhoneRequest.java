package com.microservices.dtos.requests;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

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
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(description = "Request for phones in create user")
@SuppressWarnings({"java:S2221", "java:S1192", "java:S2133", "java:S2629", "DuplicatedCode"})
public class PhoneRequest implements Serializable {
    @ApiModelProperty(name = "number", value = "number", example = "Juan Rodriguez")
    @Pattern(regexp = "^\\d{7,16}$", message = "The field number must contain only numeris and a minimum length of 7 and a maximum length of 16")
    String number;

    @ApiModelProperty(name = "citycode", value = "citycode", example = "juan@rodriguez.org")
    @Pattern(regexp = "^\\d{1,2}$", message = "The field citycode must contain only numeris and a minimum length of 1 and a maximum length of 2")
    String citycode;

    @ApiModelProperty(name = "contrycode", value = "contrycode", example = "Darwin.123+")
    @Pattern(regexp = "^\\d{1,2}$", message = "The field contrycode must contain only numeris and a minimum length of 1 and a maximum length of 2")
    String contrycode;
}
