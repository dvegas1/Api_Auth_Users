package com.microservices.components.interceptors;

import com.microservices.constants.InterceptorConstants;
import com.microservices.constants.LoggerConstants;
import com.microservices.context.AppSessionContext;
import com.microservices.dtos.commons.ApplicationSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * AppLoggerInterceptor
 * <p>
 * AppLoggerInterceptor class.
 * <p>
 * ESTE COMPONENTE FUE CONSTRUIDO SIGUIENDO LOS ESTANDARES DE DESARROLLO Y EL PROCEDIMIENTO
 * DE DESARROLLO DE APLICACIONES..
 *
 * @author Darwin Vegas.
 * @author darwinvegas1@gmail.com
 * @since 21/04/2024
 */
public class AppLoggerInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LogManager.getLogger(AppLoggerInterceptor.class);
    @Autowired
    private AppSessionContext appSessionContext;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        ThreadContext.put(LoggerConstants.KEY_USER_ID, request.getHeader(InterceptorConstants.HEADER_USER_ID));
        ThreadContext.put(LoggerConstants.KEY_REQUEST_ID, request.getHeader(InterceptorConstants.HEADER_REQUEST_ID));
        ThreadContext.put(LoggerConstants.KEY_TENANT_ID, request.getHeader(InterceptorConstants.HEADER_TENANT_ID));

        LOGGER.info("appSessionContext {}", appSessionContext);

        appSessionContext.setApplicationSession(
            ApplicationSession.builder()
                .userId(request.getHeader(InterceptorConstants.HEADER_USER_ID))
                .requestId(request.getHeader(InterceptorConstants.HEADER_REQUEST_ID))
                .tenantId(request.getHeader(InterceptorConstants.HEADER_TENANT_ID))
                .build()
        );

        LOGGER.trace("userId {}", request.getHeader(InterceptorConstants.HEADER_USER_ID));
        LOGGER.trace("requestId {}", request.getHeader(InterceptorConstants.HEADER_REQUEST_ID));

        return true;
    }
}
