package com.capstonelegal.config;

/**
 * Contains configuration information for the Swagger UI.
 * The Swagger UI is a tool for testing and documenting REST APIs.
 * It is enabled via the @EnableSwagger2 annotation.
 * <p>
 * This class is annotated with @Configuration to indicate that it is a Spring configuration class.
 * Configuration classes in Spring can have methods annotated with @Bean.
 * When a class is annotated with @Configuration, it means that the methods of this class will return objects that will be registered in the Spring context as beans.
 *
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2  // Enable Swagger 2 for this application
public class SwaggerConfig {

    /**
     * This method returns a Docket bean which is used by the Swagger UI to generate the API docs.
     * <p>
     * The Docket bean is customized to create API documentation for all the APIs
     * under the package "com.capstonelegal.judge.controller" and for all paths.
     * The API documentation is of the type Swagger 2.
     *
     * @return a Docket object configured to document APIs under "com.capstonelegal.judge.controller" package
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.capstonelegal"))
                .paths(PathSelectors.any())
                .build();
    }



    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Capstone Legal",
                "APIs related to Capstone Legal software system.",
                "API TOS",
                "Terms of service",
                new Contact("Deepthi Dayanand", "www.capstonelegal.com", "deepthi.bhd@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
}

