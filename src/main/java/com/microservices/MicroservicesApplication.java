package com.microservices;

import com.microservices.components.configurations.MicroservicesConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
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
        "com.microservices.controllers",
        "com.microservices.controllers.contracts",
        "com.microservices.dtos.base",
        "com.microservices.components.helpers",
        "com.microservices",
    }
)
@EnableCaching
public class MicroservicesApplication {

    @Autowired
    private MicroservicesConfigurer microservicesConfigurer;

    public static void main(String[] args) {
        SpringApplication.run(MicroservicesApplication.class, args);
    }
}
