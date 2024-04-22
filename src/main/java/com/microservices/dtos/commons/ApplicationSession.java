package com.microservices.dtos.commons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ApplicationSession
 * <p>
 * ApplicationSession class.
 * <p>
 * ESTE COMPONENTE FUE CONSTRUIDO SIGUIENDO LOS ESTANDARES DE DESARROLLO Y EL PROCEDIMIENTO
 * DE DESARROLLO DE APLICACIONES..
 *
 * @author Darwin Vegas.
 * @author darwinvegas1@gmail.com
 * @since 04/21/2024
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ApplicationSession {
    String userId;
    String requestId;
    String tenantId;
}
