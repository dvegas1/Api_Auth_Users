package com.microservices.dtos.commons;


import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.json.bind.annotation.JsonbProperty;

/**
 * UserInformation
 * <p>
 * UserInformation class.
 * <p>
 * ESTE COMPONENTE FUE CONSTRUIDO SIGUIENDO LOS ESTANDARES DE DESARROLLO Y EL PROCEDIMIENTO
 * DE DESARROLLO DE APLICACIONES..
 *
 * @author Darwin Vegas.
 * @author darwinvegas1@gmail.com
 * @since 10/04/2021
 */
@SuppressFBWarnings({"EI_EXPOSE_REP2", "EI_EXPOSE_REP2", "EI_EXPOSE_REP2"})
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@EqualsAndHashCode
@ApiModel(description = "Detail User")
public class StatusUser {
    @ApiModelProperty(name = "message", value = "message", example = "Successful User Create")
    @JsonbProperty("message")
    String message;

    @ApiModelProperty(name = "code", value = "code", example = "00")
    @JsonbProperty("code")
    String code;

    @ApiModelProperty(name = "user", value = "user", example = "{}")
    @JsonbProperty("user")
    DetailsUserDto user;



}
