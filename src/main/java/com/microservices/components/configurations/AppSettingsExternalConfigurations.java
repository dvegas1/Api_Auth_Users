package com.microservices.components.configurations;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * AppSettingsExternalConfigurations
 * <p>
 * AppSettingsExternalConfigurations class.
 * <p>
 * ESTE COMPONENTE FUE CONSTRUIDO SIGUIENDO LOS ESTANDARES DE DESARROLLO Y EL PROCEDIMIENTO
 * DE DESARROLLO DE APLICACIONES..
 *
 * @author Darwin Vegas.
 * @author darwinvegas1@gmail.com
 * @since 09/29/2022
 */
@SuppressFBWarnings({"EI_EXPOSE_REP2", "EI_EXPOSE_REP"})
@Component
@ConfigurationProperties(prefix = "app-configurations")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Validated

public class AppSettingsExternalConfigurations {

    @Valid
    String keyPayloadEncryption;
}
