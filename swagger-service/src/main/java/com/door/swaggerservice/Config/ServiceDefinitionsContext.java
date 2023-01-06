package com.door.swaggerservice.Config;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import springfox.documentation.swagger.web.SwaggerResource;

@Component
public class ServiceDefinitionsContext {
    
    private ConcurrentHashMap<String,String> serviceDescriptions; 

    private ServiceDefinitionsContext(){
        serviceDescriptions = new ConcurrentHashMap<String, String>();
    }

    public void addServiceDefinition(String serviceName, String serviceDescription) {
        serviceDescriptions.put(serviceName, serviceDescription);
    }

    public String getSwaggerDefinition(String serviceId) {
        return this.serviceDescriptions.get(serviceId);
    }

    public List<SwaggerResource> getSwaggerDefinition() {

        return serviceDescriptions.entrySet().stream().map( serviceDefinition -> {
            SwaggerResource resoruce = new SwaggerResource();
            resoruce.setLocation("/service/"+serviceDefinition.getKey());
            resoruce.setName(serviceDefinition.getKey());
            resoruce.setSwaggerVersion("2.0");
            return resoruce;
        }).collect(Collectors.toList());
    }

}
