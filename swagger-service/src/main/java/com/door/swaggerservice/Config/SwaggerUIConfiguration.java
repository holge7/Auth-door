package com.door.swaggerservice.Config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.swagger.web.InMemorySwaggerResourcesProvider;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

/**
 * Swagger ui configurations.
 * 
 * Configure Bean SwaggerResourcesProvider to read data from in-memory context
 * 
 */
@Configuration
public class SwaggerUIConfiguration {

    private ServiceDefinitionsContext definitionsContext;
    private RestTemplate restTemplate;

    public SwaggerUIConfiguration(ServiceDefinitionsContext definitionsContext, RestTemplate restTemplate) {
        this.definitionsContext = definitionsContext;
        this.restTemplate = restTemplate;
    }

    @Primary
    @Bean
    public SwaggerResourcesProvider swaggerResourceProvider(InMemorySwaggerResourcesProvider defaultResourcesProvider, RestTemplate RestTemplete) {

        return () -> {
            List<SwaggerResource> resoruces = new ArrayList<>(defaultResourcesProvider.get());
            resoruces.clear();
            resoruces.addAll(definitionsContext.getSwaggerDefinition());
            return resoruces;
        };
    }



}
