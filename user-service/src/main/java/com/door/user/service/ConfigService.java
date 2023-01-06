// package com.door.user.service;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
// import org.springframework.stereotype.Service;

// @Service
// public class ConfigService {

//     @Value("${springdoc.swagger-ui.path}")
//     private String swaggerEndPoint;

//     private ServletWebServerApplicationContext webServerAppCtxt;

//     public ConfigService(ServletWebServerApplicationContext webServerAppCtxt) {
//         this.webServerAppCtxt = webServerAppCtxt;
//     }
    
//     public String getDocUrl() {
//         String end = String.format("%d%s", webServerAppCtxt.getWebServer().getPort(), swaggerEndPoint);
//         return  end;
//     }

// }