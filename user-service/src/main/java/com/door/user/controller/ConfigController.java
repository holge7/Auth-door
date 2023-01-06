// package com.door.user.controller;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.door.user.service.ConfigService;
// import com.door.user.utils.ApiResponse;

// @RestController
// @RequestMapping("/api/user/admin")
// public class ConfigController {
    
//     private ConfigService configService;

//     public ConfigController(ConfigService configService) {
//         this.configService = configService;
//     }
    
//     @GetMapping("/doc")
//     public ResponseEntity<ApiResponse> swaggerDoc() {
//         return new ResponseEntity<ApiResponse>(
//                 new ApiResponse(configService.getDocUrl()),
//                 HttpStatus.OK
//             );
//     }

// }
