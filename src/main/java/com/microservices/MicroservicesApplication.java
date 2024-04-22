package com.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * MicroservicesApplication
 * <p>
 * MicroservicesApplication class.
 * <p>
 * ESTE COMPONENTE FUE CONSTRUIDO SIGUIENDO LOS ESTANDARES DE DESARROLLO Y EL PROCEDIMIENTO
 * DE DESARROLLO DE APLICACIONES..
 *
 * @author Darwin Vegas.
 * @author dawinvegas1@gmail.com
 * @since 21/04/2024
 */
@SpringBootApplication(
    scanBasePackages = {
        "com.microservices.components.helpers",
        "com.microservices",
    }
)
@EnableCaching
public class MicroservicesApplication {
    public static void main(String[] args) {
        SpringApplication.run(MicroservicesApplication.class, args);
    }
}
