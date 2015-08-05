package com.citrix.g2w.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@EnableZuulProxy
@SpringBootApplication
@RestController
@EnableCircuitBreaker
public class ApiProxyApplication {

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file) {
        return "Hello world";
    }

    @RequestMapping("/")
    public String home() {
        return "Hello.. Welcome to Home Page";
    }

    @RequestMapping(value = "/abc")
    public String homes() {
        return "Hello.. Welcome to Home Page";
    }

    @RequestMapping(value = "/authToken")
    public Map<String, String> verifyToken(@RequestHeader("token") String token,
                            @RequestHeader("userKey") String userKey,
                            @RequestHeader("grantedAuthorities") String grantedAuthorities) {
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("userKey", userKey);
        response.put("grantedAuthorities", grantedAuthorities);
        return response;
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiProxyApplication.class, args);
    }
}
