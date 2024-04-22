package com.microservices.dtos.responses;

import com.microservices.dtos.base.BaseBusinessResponseDto;
import com.microservices.dtos.commons.ApplicationInfo;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.json.bind.annotation.JsonbProperty;

/**
 * ApplicationInfoBusinessResponseDto
 * <p>
 * ApplicationInfoBusinessResponseDto class.
 * <p>
 * ESTE COMPONENTE FUE CONSTRUIDO SIGUIENDO LOS ESTANDARES DE DESARROLLO Y EL PROCEDIMIENTO
 * DE DESARROLLO DE APLICACIONES..
 *
 * @author Darwin Vegas.
 * @author darwinvegas1@gmail.com
 * @since 1/22/2021
 */
@Getter
@Setter
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "Skip by constructor")
public class ApplicationInfoBusinessResponseDto extends BaseBusinessResponseDto {

    @SuppressFBWarnings("EI_EXPOSE_REP")
    @JsonbProperty("data")
    @Schema(name = "data", description = "Business data response")
    ApplicationInfo applicationInfo;
}