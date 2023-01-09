package com.doc.docservice.service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springdoc.core.properties.AbstractSwaggerUiConfigProperties.SwaggerUrl;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import commons.utils.ApiResponse;

@Service
public class DocService {
	
	public DiscoveryClient discoveryClient;
	
	public DocService(DiscoveryClient discoveryClient) {
		this.discoveryClient = discoveryClient;
	}
	
	public ApiResponse getSwaggerURLs() {
		List<SwaggerUrl> urls = new LinkedList<>();
		
		List<String> blackListDoc = Arrays.asList("gateway-service");
		
		List<String> goodService = discoveryClient.getServices();
		goodService.stream()
				.filter(service -> !blackListDoc.contains(service))
				.forEach(serviceName -> 
					discoveryClient.getInstances(serviceName).forEach(ServiceInstance ->
					urls.add(new SwaggerUrl(serviceName, ServiceInstance.getUri()+"/swagger-ui.html", serviceName)))
				);
		
		return new ApiResponse(Map.of("urls", urls));
	}
	
	
	
}
