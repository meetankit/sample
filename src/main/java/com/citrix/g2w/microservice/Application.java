package com.citrix.g2w.microservice;

import com.citrix.g2w.microservice.api.dto.AuthenticationToken;
import com.netflix.servo.annotations.Monitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@EnableZuulProxy
@SpringBootApplication
@RestController
public class Application {

    @Autowired
    private RestTemplate restTemplate;

    private String url = "http://authed1svc.qai.expertcity.com/authentication-service/tokens/{token}";

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
        AuthenticationToken tokenResponse = restTemplate.getForObject(url, AuthenticationToken.class, token);
        System.out.println("response is=" + tokenResponse.getUserKey());
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
