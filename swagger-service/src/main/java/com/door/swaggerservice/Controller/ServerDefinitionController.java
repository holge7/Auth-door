package com.door.swaggerservice.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.door.swaggerservice.Config.ServiceDefinitionsContext;

@RestController
@RequestMapping("/api")
public class ServerDefinitionController {
    
    private ServiceDefinitionsContext serviceDefinitionsContext;

    public ServerDefinitionController(ServiceDefinitionsContext serviceDefinitionsContext) {
        this.serviceDefinitionsContext = serviceDefinitionsContext;
    }

    @GetMapping("/service/{serviceName}")
    public String getServiceDefinition(
        @PathVariable String serviceName
    ){
        System.out.println(serviceName);
        return serviceDefinitionsContext.getSwaggerDefinition(serviceName);
    }

}
