package com.microservices.context;

import com.microservices.dtos.commons.ApplicationSession;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * AppSessionContext
 * <p>
 * AppSessionContext class.
 * <p>
 * ESTE COMPONENTE FUE CONSTRUIDO SIGUIENDO LOS ESTANDARES DE DESARROLLO Y EL PROCEDIMIENTO
 * DE DESARROLLO DE APLICACIONES..
 *
 * @author Darwin Vegas.
 * @author darwinvegas1@gmail.com
 * @since 04/21/2024
 */
@SuppressFBWarnings("EI_EXPOSE_REP2")
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AppSessionContext {
    @SuppressFBWarnings("EI_EXPOSE_REP")
    ApplicationSession applicationSession;
}
