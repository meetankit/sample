package com.citrix.g2w.microservice;

import com.citrix.g2w.microservice.Service.TokenValidatorService;
import com.citrix.g2w.microservice.api.dto.AuthenticationToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@EnableZuulProxy
@SpringBootApplication
@RestController
@EnableCircuitBreaker 
public class ApiProxyApplication {

    @Autowired
    private TokenValidatorService tokenValidatorService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file) {
        return "Hello world";
    }

    @RequestMapping("/")
    public String home() {
        return "Hello.. Welcome to Home Page";
    }

    @RequestMapping(value = "/authToken/{token}")
    public void verifyToken(@PathVariable("token") String token) {
        AuthenticationToken tokenResponse = tokenValidatorService.getResponse(token);
        System.out.println("response is=" + tokenResponse.getUserKey());
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiProxyApplication.class, args);
    }
}
