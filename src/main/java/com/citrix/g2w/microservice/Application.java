package com.citrix.g2w.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableZuulProxy
@SpringBootApplication
@RestController
public class Application {
    
    @RequestMapping("/ankit")
    public String home() {
        return "Hello world";
    }
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
