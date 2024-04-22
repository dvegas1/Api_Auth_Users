package com.microservices.dtos.responses;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import com.microservices.dtos.base.BaseBusinessResponseDto;
import com.microservices.dtos.commons.StatusUser;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.json.bind.annotation.JsonbProperty;

/**
 * UserResponse
 * <p>
 * UserResponse class.
 * <p>
 * ESTE COMPONENTE FUE CONSTRUIDO SIGUIENDO LOS ESTANDARES DE DESARROLLO Y EL PROCEDIMIENTO
 * DE DESARROLLO DE APLICACIONES..
 *
 * @author Darwin Vegas.
 * @author darwinvegas1@gmail.com
 * @since 10/04/2021
 */
@Getter
@Setter
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuppressFBWarnings(value = {"EI_EXPOSE_REP2", "BAD_PRACTICE"}, justification = "Skip by constructor")
@ApiModel(description = "Response for Information card")
@SuppressWarnings("java:S1948")
public class UserResponse extends BaseBusinessResponseDto {

    @SuppressFBWarnings("EI_EXPOSE_REP")
    @SerializedName("status")
    @JsonProperty("status")
    @JsonbProperty("status")
    @ApiModelProperty(name = "status", value = "Information status.")
    @Builder.Default
    StatusUser status = StatusUser.builder().build();
}
