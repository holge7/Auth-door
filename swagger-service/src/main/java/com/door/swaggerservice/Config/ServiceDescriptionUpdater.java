package com.door.swaggerservice.Config;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;


@Component
public class ServiceDescriptionUpdater {
    private static final Logger logger = LoggerFactory.getLogger(ServiceDescriptionUpdater.class);

    private static final String DEFAULT_SWAGGER_URL = "/v2/api-docs";
    private static final String KEY_SWAGGER_URL = "swagger_url";

        private DiscoveryClient discoveryClient;
    private final RestTemplate restTemplate;
    private ServiceDefinitionsContext serviceDefinitionsContext;

    public ServiceDescriptionUpdater(DiscoveryClient discoveryClient, RestTemplate restTemplate, ServiceDefinitionsContext serviceDefinitionsContext) {
        this.discoveryClient = discoveryClient;
        this.restTemplate = restTemplate;
        this.serviceDefinitionsContext = serviceDefinitionsContext;
    }

    public List<String> test() {
            List<String> services = discoveryClient.getServices();
            return services;
        }

    @Scheduled(fixedDelayString= "${swagger.config.refreshrate}")
    public void refreshSwaggerConfigurations() {
        logger.debug("Starting Service Definition Context refresh");

        //For each microservice register in Eureka
            discoveryClient.getServices().stream().forEach(serviceID -> {
                logger.debug("Attempting service definition refresh for Service: {}", serviceID);
                List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceID);

                if(serviceInstances == null || serviceInstances.isEmpty()) {
                    logger.warn("No instances aviable for service: {}", serviceID, serviceID);
                } else {
                    ServiceInstance instance = serviceInstances.get(0);
                    String swaggerURL = getSwaggerURL(instance);

                    Optional<Object> jsonData = getSwaggerDefinitionForAPI(serviceID, swaggerURL);

                    if (jsonData.isPresent()) {
                        String content = getJSON(serviceID, jsonData.get());
                        serviceDefinitionsContext.addServiceDefinition(serviceID, content);
                    } else {
                        logger.error("Skipping service id : {} Error : Could not get Swagegr definition from API ",serviceID);
                    }
                    logger.info("Service Definition Context Refreshed at :  {}",LocalDate.now());
                }

            });
    }

        public String getSwaggerURL(ServiceInstance instance) {
            String swaggerUrl = instance.getMetadata().get(KEY_SWAGGER_URL);
            
            if (swaggerUrl != null) {
                return instance.getUri()+swaggerUrl;
            }

            return instance.getUri()+DEFAULT_SWAGGER_URL;
        }

        public Optional<Object> getSwaggerDefinitionForAPI(String serviceName, String url) {
            logger.info("Accessing the SwaggerDefinition JSON for Service : {} : URL : {}", serviceName, url);

            try {
                Object jsonData = restTemplate.getForObject(url, Object.class);
                return Optional.of(jsonData);
            } catch (Exception e) {
                logger.error("Error while getting service definition for service : {} Error : {} ", serviceName, e.getMessage());
                return Optional.empty();
            }


        }

        public String getJSON(String serviceId, Object jsonData){
            try {
                return new ObjectMapper().writeValueAsString(jsonData);
            } catch (JsonProcessingException e) {
                logger.error("Error : {} ", e.getMessage());
                return "";
            }
        }



}
