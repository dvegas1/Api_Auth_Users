package com.microservices.dtos.commons;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;

/**
 * ApplicationInfo
 * <p>
 * ApplicationInfo class.
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
@ApiModel(description = "Application information of microservice")
public class ApplicationInfo implements Serializable {

    @Builder.Default
    @ApiModelProperty(name = "userHome", value = "User home directory")
    String userHome = System.getProperty("user.home");

    @ApiModelProperty(name = "helmTemplateVersion", value = "Helm template version")
    String helmTemplateVersion;

    @ApiModelProperty(name = "microserviceVersion", value = "Microservice version")
    String microserviceVersion;

    @ApiModelProperty(name = "microserviceName", value = "Microservice name")
    String microserviceName;

    @ApiModelProperty(name = "userHome", value = "User home directory")
    @Value("${hazelcastYmlPath}")
    String hazelcastYmlPath;

    @ApiModelProperty(name = "userHome", value = "User home directory")
    @Builder.Default
    String osArch = System.getProperty("os.arch");

    @ApiModelProperty(name = "userHome", value = "User home directory")
    @Builder.Default
    String javaVendor = System.getProperty("java.vendor");
}
