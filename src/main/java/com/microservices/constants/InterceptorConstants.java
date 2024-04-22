package com.microservices.constants;

/**
 * InterceptorConstants
 * <p>
 * InterceptorConstants class.
 * <p>
 * ESTE COMPONENTE FUE CONSTRUIDO SIGUIENDO LOS ESTANDARES DE DESARROLLO Y EL PROCEDIMIENTO
 * DE DESARROLLO DE APLICACIONES..
 *
 * @author Darwin Vegas.
 *
 * @author dawinvegas1@gmail.com
 * @since 04/21/2024
 */
public class InterceptorConstants {

    private InterceptorConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String HEADER_USER_ID = "X-user-id";
    public static final String HEADER_REQUEST_ID = "X-request-id";
    public static final String HEADER_TENANT_ID = "X-Tenant-Id";
}
