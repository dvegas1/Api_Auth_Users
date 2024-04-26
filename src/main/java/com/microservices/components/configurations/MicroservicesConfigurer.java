package com.microservices.components.configurations;

import com.fasterxml.classmate.TypeResolver;
import com.microservices.components.converters.RequestParameterConverter;
import com.microservices.components.interceptors.AppLoggerInterceptor;
import com.microservices.constants.CategoryRestConstants;
import com.microservices.dtos.base.BaseBusinessResponseDto;
import com.microservices.dtos.messages.GenericMessagesBusinessResponse;
import com.microservices.dtos.messages.MessageBusinessResponse;
import com.microservices.dtos.requests.CreateUserRequest;
import com.microservices.dtos.responses.ApplicationInfoBusinessResponseDto;
import com.microservices.dtos.responses.UserResponse;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.NonNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.WebFluxRequestHandlerProvider;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * MicroservicesConfigurer
 * <p>
 * MicroservicesConfigurer class.
 * <p>
 * ESTE COMPONENTE FUE CONSTRUIDO SIGUIENDO LOS ESTANDARES DE DESARROLLO Y EL PROCEDIMIENTO
 * DE DESARROLLO DE APLICACIONES..
 *
 * @author Darwin Vegas.
 * @author darwinvegas1@gmail.com
 * @since 21/04/2024
 */
@Configuration
@EnableSwagger2
@EnableOpenApi
@EnableConfigurationProperties(AppSettingsExternalConfigurations.class)
@Import(BeanValidatorPluginsConfiguration.class)
public class MicroservicesConfigurer implements WebMvcConfigurer {

    private static final String URL = "localhost";
    TypeResolver typeResolver;
    String appName;
    String appVersion;
    ListableBeanFactory beanFactory;

    @Autowired
    public MicroservicesConfigurer(TypeResolver typeResolver,
                                   @Value("${spring.application.name}") String appName,
                                   @Value("${spring.application.version}") String appVersion,
                                   ListableBeanFactory beanFactory) {
        this.typeResolver = typeResolver;
        this.appName = appName;
        this.appVersion = appVersion;
        this.beanFactory = beanFactory;
    }

    @Bean
    public static BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
        return new BeanPostProcessor() {

            @Override
            public Object postProcessAfterInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
                if (bean instanceof WebMvcRequestHandlerProvider || bean instanceof WebFluxRequestHandlerProvider) {
                    customizeSpringfoxHandlerMappings(getHandlerMappings(bean));
                }
                return bean;
            }

            private <T extends RequestMappingInfoHandlerMapping> void customizeSpringfoxHandlerMappings(List<T> mappings) {
                List<T> copy = mappings.stream()
                    .filter(mapping -> mapping.getPatternParser() == null)
                    .collect(Collectors.toList());
                mappings.clear();
                mappings.addAll(copy);
            }

            @SuppressWarnings({"unchecked", "java:S2259", "java:S3011"})
            @SuppressFBWarnings("NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE")
            private List<RequestMappingInfoHandlerMapping> getHandlerMappings(@NonNull Object bean) {
                try {
                    Field field = ReflectionUtils.findField(bean.getClass(), "handlerMappings");
                    Objects.requireNonNull(field).setAccessible(true);
                    return (List<RequestMappingInfoHandlerMapping>) field.get(bean);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    throw new IllegalStateException(e);
                }
            }
        };
    }

    @Override
    public void addFormatters(@NonNull FormatterRegistry registry) {
        beanFactory.getBeansWithAnnotation(RequestParameterConverter.class)
            .values()
            .parallelStream()
            .filter(Converter.class::isInstance)
            .forEach(c -> registry.addConverter((Converter<?, ?>) c));
    }

    @Bean
    public AppLoggerInterceptor appLoggerInterceptor() {
        return new AppLoggerInterceptor();
    }

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
            .title(appName)
            .description("<br>Start your execution test. <br><br> ESTE COMPONENTE FUE CONSTRUIDO SIGUIENDO LOS ESTANDARES DE DESARROLLO Y EL PROCEDIMIENTO DE DESARROLLO DE APLICACIONES.")
            .termsOfServiceUrl(URL)
            .contact(new Contact("darwinvegas1@gmail.com", URL, "darwinvegas1@gmail.com"))
            .version(appVersion)
            .build();
    }

    @Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
            .deepLinking(true)
            .displayOperationId(false)
            .defaultModelsExpandDepth(1)
            .defaultModelExpandDepth(1)
            .defaultModelRendering(ModelRendering.EXAMPLE)
            .displayRequestDuration(false)
            .docExpansion(DocExpansion.NONE)
            .filter(false)
            .maxDisplayedTags(null)
            .operationsSorter(OperationsSorter.ALPHA)
            .showExtensions(false)
            .showCommonExtensions(false)
            .tagsSorter(TagsSorter.ALPHA)
            .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
            .validatorUrl(null)
            .build();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        var loggerInterceptor = registry.addInterceptor(appLoggerInterceptor());
        loggerInterceptor.order(0);
    }

    @Bean
    public Docket microserviceApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .groupName("microservice-api")
            .useDefaultResponseMessages(true)
            .additionalModels(
                typeResolver.resolve(BaseBusinessResponseDto.class),
                typeResolver.resolve(ApplicationInfoBusinessResponseDto.class),
                typeResolver.resolve(GenericMessagesBusinessResponse.class),
                typeResolver.resolve(MessageBusinessResponse.class),
                typeResolver.resolve(UserResponse.class),
                typeResolver.resolve(CreateUserRequest.class)
            )
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.microservices.controllers"))
            .paths(PathSelectors.any())
            .build()
            .ignoredParameterTypes(ApiIgnore.class)
            .tags(
                new Tag(CategoryRestConstants.CATEGORY_INTERACTIVE_RESPONSE_MANAGEMENT, CategoryRestConstants.CATEGORY_BUSINESS_DESCRIPTION)
            );
    }
    @Bean
    @SuppressWarnings({"java:S1611"})
    MeterRegistryCustomizer<MeterRegistry> configurer(@Value("${spring.application.name}") String applicationName) {
        return (registry) -> registry.config().commonTags("application", applicationName);
    }

}
