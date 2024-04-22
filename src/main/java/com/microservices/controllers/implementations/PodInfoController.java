package com.microservices.controllers.implementations;

import com.microservices.components.enums.ResponseCode;
import com.microservices.constants.ProcessConstants;
import com.microservices.context.AppSessionContext;
import com.microservices.controllers.contracts.IPodInfoController;
import com.microservices.dtos.base.ApiBussinesResponse;
import com.microservices.dtos.commons.ApplicationInfo;
import com.microservices.dtos.responses.ApplicationInfoBusinessResponseDto;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * PodInfoController
 * <p>
 * PodInfoController class. Controlador requerido para la verificación de las configuraciones basicas de un pod en k8s
 * <p>
 * ESTE COMPONENTE FUE CONSTRUIDO SIGUIENDO LOS ESTANDARES DE DESARROLLO Y EL PROCEDIMIENTO DE DESARROLLO DE
 * APLICACIONES.
 *
 * @author Darwin Vegas.
 * @author darwinvegas1@gmail.com
 * @since 14/08/2020
 */
@RestController
public class PodInfoController implements IPodInfoController {

    private static final Logger LOGGER = LogManager.getLogger(PodInfoController.class);

    AppSessionContext appSessionContext;
    ApiBussinesResponse businessResponse;

    @Value("${spring.application.version:unknown}")
    String microserviceVersion;

    @Value("${spring.application.helmTemplateVersion:unknown}")
    String helmTemplateVersion;

    @Value("${spring.application.name}")
    String microserviceName;

    @SuppressFBWarnings("EI_EXPOSE_REP2")
    public PodInfoController(AppSessionContext appSessionContext,
                             ApiBussinesResponse businessResponse) {
        this.appSessionContext = appSessionContext;
        this.businessResponse = businessResponse;
    }

    @Override
    public ResponseEntity<Object> getPodInfo() {
        LOGGER.info("Session context: {}", appSessionContext);

        return businessResponse.getResponse(
            ApplicationInfoBusinessResponseDto.builder()
                .applicationInfo(ApplicationInfo.builder()
                    .microserviceVersion(microserviceVersion)
                    .helmTemplateVersion(helmTemplateVersion)
                    .microserviceName(microserviceName)
                    .build())
                .messageResponse(ProcessConstants.PROCESS_SUCESSFULLY_MSG)
                .messageResponseCode(ProcessConstants.PROCESS_SUCESSFULLY_CODE)
                .build()
            ,
            ResponseCode.PROCESS_OK.getResponseCodeValue()
        );
    }
}
