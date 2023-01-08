package com.doc.docservice.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springdoc.core.properties.AbstractSwaggerUiConfigProperties.SwaggerUrl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.cloud.client.discovery.DiscoveryClient;

@RestController
@RequestMapping("/api/doc")
public class DocController {
	
	public DiscoveryClient discoveryClient;
	
	public DocController(DiscoveryClient discoveryClient) {
		this.discoveryClient = discoveryClient;
	}
	
	@GetMapping("/all")
	public Map<String, Object> swaggerConfig() {
		List<SwaggerUrl> urls = new LinkedList<>();
		discoveryClient.getServices().forEach(serviceName -> 
					discoveryClient.getInstances(serviceName).forEach(ServiceInstance ->
						urls.add(new SwaggerUrl(serviceName, ServiceInstance.getUri()+"/swagger-ui.html", serviceName)))
				);
		return Map.of("urls", urls);
	}
	
	
	
}
