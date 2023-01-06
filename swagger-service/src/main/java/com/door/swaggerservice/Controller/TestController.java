package com.door.swaggerservice.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.door.swaggerservice.Config.ServiceDescriptionUpdater;

@RestController
@RequestMapping("/api/test")
public class TestController {
    
    public ServiceDescriptionUpdater serviceDescriptionUpdater;

    public TestController(ServiceDescriptionUpdater serviceDescriptionUpdater) {
        this.serviceDescriptionUpdater = serviceDescriptionUpdater;
    }

    @GetMapping("")
    public String test() {
        return "Hello World!";
    }

    @GetMapping("/eureka")
    public List<String> eurka() {
        return serviceDescriptionUpdater.test();
    }

}
